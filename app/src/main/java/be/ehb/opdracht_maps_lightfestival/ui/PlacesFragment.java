package be.ehb.opdracht_maps_lightfestival.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import be.ehb.opdracht_maps_lightfestival.R;
import be.ehb.opdracht_maps_lightfestival.model.Location;
import be.ehb.opdracht_maps_lightfestival.model.LocationDAO;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlacesFragment extends Fragment {
    private MapView mapView;
    private GoogleMap myMap;

    private OnMapReadyCallback onMapReady = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            myMap = googleMap;

            LatLng coordBrussel = new LatLng(50.858712, 4.347446);
            CameraUpdate moveToBrussel = CameraUpdateFactory.newLatLngZoom(coordBrussel, 16);
            myMap.animateCamera(moveToBrussel);

            myMap.setOnInfoWindowClickListener(infoWindowClickListener);
            setMarkerAdapter();
            drawMarkers();
        }

    };
    private GoogleMap.OnInfoWindowClickListener infoWindowClickListener = new GoogleMap.OnInfoWindowClickListener() {
        @Override
        public void onInfoWindowClick(Marker marker) {
            Location l = (Location) marker.getTag();
            //ipv toast eenlink naar een ander scherm met de info er in
            Toast.makeText(getActivity(), l.getActivity(), Toast.LENGTH_LONG).show();

        }
    };

    private void setMarkerAdapter() {
        myMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

               View cardView = getActivity().getLayoutInflater().inflate(R.layout.marker_card, null, false );

                TextView tvTitle = cardView.findViewById(R.id.tv_card_title);
                TextView tvSnippet = cardView.findViewById(R.id.tv_card_snippet);
                tvTitle.setText(marker.getTitle());
                tvSnippet.setText(marker.getSnippet());
                return cardView;
            }
        });
    }

    private void drawMarkers() {
        
        for (Location location : LocationDAO.INSTANCE.getLocations()) {
            Marker m = myMap.addMarker(new MarkerOptions().position(location.getCoordinate()));
            m.setTitle(location.getName());
            m.setSnippet(location.getActivity());
            m.setTag(location);
            m.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));

        }
    }


    public PlacesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_places, container, false);

        mapView = rootView.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(onMapReady);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}

