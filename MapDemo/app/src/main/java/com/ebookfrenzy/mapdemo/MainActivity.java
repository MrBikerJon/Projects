package com.ebookfrenzy.mapdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.res.ResourcesCompat;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ebookfrenzy.mapdemo.databinding.ActivityMainBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GoogleMap.OnMarkerClickListener {

    // TODO place markers on map using SQL database

    private ActivityMainBinding binding;

    /**
     * obtain a reference to the fragment_text instance and call the changeText() method on the object
     * @param marker
     */
    public boolean onMarkerClick(Marker marker) {

        MapsFragment mMap = (MapsFragment) getSupportFragmentManager().findFragmentById(R.id.map2);

        // the key for the HashMap is the Marker Title
        String key = marker.getTitle();

        // get the PointOfInterest object from the HashMap using the key
        PointOfInterest poi = mMap.getPointOfInterest(key);

        // get the text and photo from the PointOfInterest object
        String newTitleText = poi.getPlaceTitle();
        String newDescriptionText = poi.getPlaceDescription();
        ArrayList<Drawable> photos = poi.getPlacePhotos();

        TextFragment textFragment = (TextFragment) getSupportFragmentManager().findFragmentById(R.id.text);

        // clear text and photos
        textFragment.clearTextFragment();

        // Set the text in the Text fragment
        textFragment.addText(newTitleText);
        textFragment.addText(newDescriptionText);

        // Set the photo(s) in the Text fragment
        for(int i = 0; i < photos.size(); i++) {
            Drawable newImage = photos.get(i);
            textFragment.addPhoto(newImage);
        }

        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // code to determine screen orientation
        int orientation = this.getResources().getConfiguration().orientation;
        ConstraintSet set = new ConstraintSet();
        ConstraintLayout layout;
        layout = (ConstraintLayout) findViewById(R.id.activity_main);
        set.clone(layout);
        // Break the connections
        breakPhotoConstraints(set);

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Set up new connections
            set.connect(R.id.map2, ConstraintSet.END, R.id.activity_main, ConstraintSet.END, 0);
            set.connect(R.id.text, ConstraintSet.START, R.id.activity_main, ConstraintSet.START, 0);
            set.connect(R.id.map2, ConstraintSet.BOTTOM, R.id.text, ConstraintSet.TOP, 0);
            set.connect(R.id.text, ConstraintSet.TOP, R.id.map2, ConstraintSet.BOTTOM, 0);
        } else {
            // Set up new connections
            set.connect(R.id.map2, ConstraintSet.END, R.id.text, ConstraintSet.START, 0);
            set.connect(R.id.text, ConstraintSet.START, R.id.map2, ConstraintSet.END, 0);
            set.connect(R.id.map2, ConstraintSet.BOTTOM, R.id.activity_main, ConstraintSet.BOTTOM, 0);
            set.connect(R.id.text, ConstraintSet.TOP, R.id.activity_main, ConstraintSet.TOP, 0);
        }
        set.applyTo(layout);
    }

    private void breakPhotoConstraints(ConstraintSet set) {
        set.clear(R.id.map2, ConstraintSet.BOTTOM);
        set.clear(R.id.text, ConstraintSet.TOP);
        set.clear(R.id.map2, ConstraintSet.END);
        set.clear(R.id.text, ConstraintSet.START);
    }

    @Override
    public void onStart() {
        super.onStart();
        addPhotosToPointsOfInterest();
    }

    private void addPhotosToPointsOfInterest() {
        // TODO fix this spaghetti code - can't access the res/drawable folder from within the map fragment so have added it here temporarily

        // This adds the photos into the PointsOfInterest HashMap
        MapsFragment mMap = (MapsFragment) getSupportFragmentManager().findFragmentById(R.id.map2);

        Drawable myImage = ResourcesCompat.getDrawable(getResources(), R.drawable.fishermanscottage, null);
        PointOfInterest poi = mMap.getPointOfInterest("Fisherman's Cottage");
        poi.addPlacePhoto(myImage);


        myImage = ResourcesCompat.getDrawable(getResources(), R.drawable.redlionhotel, null);
        Drawable myImage2 = ResourcesCompat.getDrawable(getResources(), R.drawable.redlioninnoldback, null);
        poi = mMap.getPointOfInterest("Red Lion Hotel");
        poi.addPlacePhoto(myImage);
        poi.addPlacePhoto(myImage2);

        myImage = ResourcesCompat.getDrawable(getResources(), R.drawable.rnli, null);
        poi = mMap.getPointOfInterest("RNLI Lifeboat Station");
        poi.addPlacePhoto(myImage);
    }

}