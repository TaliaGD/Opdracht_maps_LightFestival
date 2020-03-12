package be.ehb.opdracht_maps_lightfestival.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class LocationDAO {

    public static LocationDAO INSTANCE = new LocationDAO();
    private ArrayList<Location> locations;

    public LocationDAO() {
    }

    public ArrayList<Location>getLocations(){
        if (locations == null){
            locations = new ArrayList<>();
            locations.add(new Location(new LatLng(50.858712, 4.347446), "het kaaitheater","komt een boot met vuurwerk langs" ));
            locations.add(new Location(new LatLng(50.860215, 4.350880), "het maximiliaan park","een interactieve projectie" ));
            locations.add(new Location(new LatLng(50.863994, 4.349828), "magasin4","een lasershow op muziek" ));
        }
        return locations;
    }
}
