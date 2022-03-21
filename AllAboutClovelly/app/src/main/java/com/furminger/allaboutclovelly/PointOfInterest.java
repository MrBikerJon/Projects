package com.furminger.allaboutclovelly;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class PointOfInterest {

    private int id;
    private double latitude;
    private double longitude;
    private String placeTitle;
    private String placeDescription;
    private ArrayList<String> placePhoto;

    public PointOfInterest() {
        placePhoto  = new ArrayList<>();
    }

    public PointOfInterest(int id, double latitude, double longitude, String placeTitle, String placeDescription, ArrayList<String> placePhoto) {
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

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPlaceTitle() {
        return placeTitle;
    }

    public void setPlaceTitle(String placeTitle) {
        this.placeTitle = placeTitle;
    }

    public String getPlaceDescription() {
        return placeDescription;
    }

    public void setPlaceDescription(String placeDescription) {
        this.placeDescription = placeDescription;
    }

    public ArrayList<String> getPlacePhotos() {
        return placePhoto;
    }

    public void setPlacePhoto(String photoResourceName) {
        this.placePhoto.add(photoResourceName);
    }

//    public void addPlacePhoto(Drawable drawable) { placePhoto.add(drawable); }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
