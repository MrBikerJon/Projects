package com.furminger.allaboutclovelly;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class PointOfInterest {

    private int id;
    private double latitude;
    private double longitude;
    private String placeTitle;
    private String placeDescription;
    private ArrayList<Drawable> placePhoto;

    public PointOfInterest(int id, double latitude, double longitude, String placeTitle, String placeDescription, ArrayList<Drawable> placePhoto) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.placeTitle = placeTitle;
        this.placeDescription = placeDescription;
        this.placePhoto = placePhoto;
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

    public ArrayList<Drawable> getPlacePhotos() {
        return placePhoto;
    }

    public void addPlacePhoto(Drawable drawable) { placePhoto.add(drawable); }

}
