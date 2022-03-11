package com.ebookfrenzy.mapdemo;

public class PointOfInterest {

    private double latitude;
    private double longitude;
    private String placeTitle;
    private String placeDescription;

    public PointOfInterest(double latitude, double longitude, String placeTitle, String placeDescription) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.placeTitle = placeTitle;
        this.placeDescription = placeDescription;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getPlaceTitle() {
        return placeTitle;
    }

    public String getPlaceDescription() {
        return placeDescription;
    }


}
