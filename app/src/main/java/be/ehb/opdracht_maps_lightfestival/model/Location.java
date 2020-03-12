package be.ehb.opdracht_maps_lightfestival.model;

import com.google.android.gms.maps.model.LatLng;

public class Location {
    private LatLng coordinate;
    private String name, activity;

    public Location(LatLng coordinate, String name, String activity) {
        this.coordinate = coordinate;
        this.name = name;
        this.activity = activity;
    }

    public LatLng getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(LatLng coordinate) {
        this.coordinate = coordinate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
