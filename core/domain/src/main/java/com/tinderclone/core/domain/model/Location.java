package com.tinderclone.core.domain.model;

public class Location {
    private double lat;
    private double lng;

    public Location() {
        // Default constructor required for calls to DataSnapshot.getValue(Location.class)
    }

    public Location(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
