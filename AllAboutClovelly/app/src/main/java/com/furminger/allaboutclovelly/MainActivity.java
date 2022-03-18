package com.furminger.allaboutclovelly;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.furminger.allaboutclovelly.databinding.ActivityMainBinding;
import com.furminger.allaboutclovelly.ui.main.ListFragment;
import com.furminger.allaboutclovelly.ui.main.MapsFragment;
import com.furminger.allaboutclovelly.ui.main.TextFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements GoogleMap.OnMarkerClickListener {

    // TODO place markers on map using SQL database

    private ActivityMainBinding binding;
    private final int MAP_TEXT_FRAGMENTS = 0;
    private final int LIST_FRAGMENT = 1;
    private int currentFragment = MAP_TEXT_FRAGMENTS;

    private FragmentManager fragmentManager;
    private Fragment mapsFragment;
    private Fragment textFragment;
    private Fragment listFragment;

    private Map<String, PointOfInterest> pointsOfInterest = new HashMap<>();

    private Drawable drawable;

    private final String TAG = "mapDemo";


    private String currentPointOfInterestKey;

    private PointOfInterest visitorsCentre = new PointOfInterest(0, 50.99922758761011, -4.40493487281753,
            "Visitors Centre",
            "The visitors centre is where .......",
            new ArrayList<Drawable>());
    private PointOfInterest donkeyStables = new PointOfInterest(1, 50.999443650702425, -4.402059544653397,
            "Donkey Stables",
            "The donkey stables at Clovelly are almost as old as the village itself.",
            new ArrayList<Drawable>());
    private PointOfInterest fishermansCottage = new PointOfInterest(2, 50.99795648517228, -4.399230743006255,
            "Fisherman's Cottage",
            "Inside the " +
                    "cottage you can see how a Clovelly fisherman and his family lived in the 1930s. The " +
                    "parlour is decorated with domestic treasures of the period, including simple cottage " +
                    "furniture, colourful pictures and religious engravings. The tiny kitchen is plain but " +
                    "full of period charm. Upstairs there are two small bedrooms, a sail loft, and an attic " +
                    "complete with straw mattresses.",
            new ArrayList<Drawable>());
    private PointOfInterest redLionHotel = new PointOfInterest(3, 50.99907263622343, -4.397884284339087,
            "Red Lion Hotel",
            "The Red Lion Hotel is an 18th Century 4-star Inn that stands on the quay alongside Clovelly’s " +
                    "ancient harbour.",
            new ArrayList<Drawable>());
    private PointOfInterest RNLILifeboatStation = new PointOfInterest(4, 50.99836498982892, -4.397444391999562,
            "RNLI Lifeboat Station",
            "Following " +
                    "a terrible storm Clovelly’s first lifeboat station was built in 1870. Most of the " +
                    "fishing fleet was destroyed with the loss of many lives. At only 33 feet long and " +
                    "built of wood, the lifeboat was powered through the waves by a crew of sturdy rowers.",
            new ArrayList<Drawable>());
    private PointOfInterest clovellyCourtGardens = new PointOfInterest(5, 51.000409170390604, -4.4104924096382225,
            "Clovelly Court Gardens",
            "Charming walled garden, a few minutes drive from the Clovelly village car park .",
            new ArrayList<Drawable>());

    /**
     * obtain a reference to the fragment_text instance and call the changeText() method on the object
     * @param marker
     */
    public boolean onMarkerClick(Marker marker) {

//        MapsFragment mMap = (MapsFragment) getSupportFragmentManager().findFragmentById(R.id.map2);

        // the key for the HashMap is the Marker Title
        String key = marker.getTitle();

        // set the current PointOfInterest in the ViewModel *************
        setCurrentPointOfInterest(key);

        // get the PointOfInterest object from the HashMap using the key *************
        PointOfInterest poi = getPointOfInterest(key);

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

        // create the database **************
        createPointsOfInterestDatabase();

        // create a list fragment
        ListFragment newListFragment = new ListFragment();

        // create a fragment manager
        fragmentManager = getSupportFragmentManager();
        // add a new list_fragment to the view, with a tag so it can be accessed by the fragment manager
        fragmentManager.beginTransaction()
                .add(R.id.activity_main, newListFragment, "list_tag")
                .commitNow();

        // allocate Fragments so they can be accessed by fragment manager
        mapsFragment = getSupportFragmentManager().findFragmentByTag("map_tag");
        textFragment = getSupportFragmentManager().findFragmentByTag("text_tag");
        listFragment = getSupportFragmentManager().findFragmentByTag("list_tag");

        // display correct Fragment(s) depending on view chosen by user
        if(currentFragment == MAP_TEXT_FRAGMENTS) {
            showMapTextFragments();
        }
        else if(currentFragment == LIST_FRAGMENT) {
            showListFragment();
        }

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // display correct Fragment(s) depending on view chosen by user
                if(currentFragment == MAP_TEXT_FRAGMENTS) {
                    showListFragment();
                    currentFragment = LIST_FRAGMENT;
                }
                else {
                    showMapTextFragments();
                    currentFragment = MAP_TEXT_FRAGMENTS;
                }
            }
        });
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

        // add all photos in the View Model *********************************
        // can this be done somewhere else? **********************
        addPhotosToPointsOfInterest();
    }


    public void showMapTextFragments() {
        // hide the ListFragment
        fragmentManager.beginTransaction()
                .hide(listFragment)
                // show the map and text fragment
                .show(mapsFragment)
                .show(textFragment)
                .commitNow();

        // code to determine screen orientation
        int orientation = this.getResources().getConfiguration().orientation;
        ConstraintSet set = new ConstraintSet();
        ConstraintLayout layout;

        layout = findViewById(R.id.activity_main);
        set.clone(layout);
        // Break the connections
        breakPhotoConstraints(set);

        // Set up new connections
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            set.connect(R.id.map2, ConstraintSet.END, R.id.activity_main, ConstraintSet.END, 0);
            set.connect(R.id.text, ConstraintSet.START, R.id.activity_main, ConstraintSet.START, 0);
            set.connect(R.id.map2, ConstraintSet.BOTTOM, R.id.text, ConstraintSet.TOP, 0);
            set.connect(R.id.text, ConstraintSet.TOP, R.id.map2, ConstraintSet.BOTTOM, 0);
        } else {
            set.connect(R.id.map2, ConstraintSet.END, R.id.text, ConstraintSet.START, 0);
            set.connect(R.id.text, ConstraintSet.START, R.id.map2, ConstraintSet.END, 0);
            set.connect(R.id.map2, ConstraintSet.BOTTOM, R.id.activity_main, ConstraintSet.BOTTOM, 0);
            set.connect(R.id.text, ConstraintSet.TOP, R.id.activity_main, ConstraintSet.TOP, 0);
        }
        set.applyTo(layout);
    }

    public void showListFragment() {

        // hide the List and map Fragments
        fragmentManager.beginTransaction()
                .hide(mapsFragment)
                .hide(textFragment)
                // show the list fragment
                .show(listFragment)
                .commitNow();
    }


    /**
     * This method adds the photos into the PointsOfInterest HashMap
     * Doing it here as can't access drawable resources from within ViewModel
     * see: https://stackoverflow.com/questions/51451819/how-to-get-context-in-android-mvvm-viewmodel
     *
     * TODO try putting just the name of the drawable inside the HashMap, then having the TextFragment and List Fragment extract using R.id.nameOfDrawable
     *
     */
    public void addPhotosToPointsOfInterest() {

        drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.visitorcentre, null);
        PointOfInterest poi = getPointOfInterest("Visitors Centre");
        poi.addPlacePhoto(drawable);

        drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.donkeystables, null);
        poi = getPointOfInterest("Donkey Stables");
        poi.addPlacePhoto(drawable);

        drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.fishermanscottage, null);
        poi = getPointOfInterest("Fisherman's Cottage");
        poi.addPlacePhoto(drawable);

        drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.redlionhotel, null);
        poi = getPointOfInterest("Red Lion Hotel");
        poi.addPlacePhoto(drawable);
        drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.redlioninnoldback, null);
        poi.addPlacePhoto(drawable);

        drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.rnli, null);
        poi = getPointOfInterest("RNLI Lifeboat Station");
        poi.addPlacePhoto(drawable);

        drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.clovellycourtgardens, null);
        poi = getPointOfInterest("Clovelly Court Gardens");
        poi.addPlacePhoto(drawable);
    }

    public Map<String, PointOfInterest> getPointsOfInterest() {
        return pointsOfInterest;
    }


    public PointOfInterest getPointOfInterest(String key) {
        return pointsOfInterest.get(key);
    }


    public void createPointsOfInterestDatabase() {
        pointsOfInterest.put("Visitors Centre", visitorsCentre);
        pointsOfInterest.put("Donkey Stables", donkeyStables);
        pointsOfInterest.put("Fisherman's Cottage", fishermansCottage);
        pointsOfInterest.put("Red Lion Hotel", redLionHotel);
        pointsOfInterest.put("RNLI Lifeboat Station", RNLILifeboatStation);
        pointsOfInterest.put("Clovelly Court Gardens", clovellyCourtGardens);
    }


    public String getCurrentPointOfInterest() {
        return currentPointOfInterestKey;
    }

    public void setCurrentPointOfInterest(String currentPointOfInterestKey) {
        this.currentPointOfInterestKey = currentPointOfInterestKey;
    }




}