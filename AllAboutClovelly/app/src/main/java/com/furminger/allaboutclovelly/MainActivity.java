package com.furminger.allaboutclovelly;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.furminger.allaboutclovelly.databinding.ActivityMainBinding;
import com.furminger.allaboutclovelly.ui.main.ListFragment;
import com.furminger.allaboutclovelly.ui.main.MapsFragment;
import com.furminger.allaboutclovelly.ui.main.TextFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements GoogleMap.OnMarkerClickListener {

    private final String TAG = "mapDemo";

    private final int MAP_TEXT_FRAGMENTS = 0;
    private final int LIST_FRAGMENT = 1;
    private int currentFragment = MAP_TEXT_FRAGMENTS;
    private FragmentManager fragmentManager;
    private Fragment mapsFragment;
    private Fragment textFragment;
    private Fragment listFragment;
    private FloatingMarkerTitlesOverlay floatingMarkerTitlesOverlay;
    private Fragment detailFragment;

    private final Map<String, PointOfInterest> pointsOfInterest = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.furminger.allaboutclovelly.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // looking for "W/System: A resource failed to call close" in Logcat
        try {
            Class.forName("dalvik.system.CloseGuard")
                    .getMethod("setEnabled", boolean.class)
                    .invoke(null, true);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }

        // create the pointsOfInterest database
        parseXML();

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

        floatingMarkerTitlesOverlay = findViewById(R.id.mapoverlay);

        // display correct Fragment(s) depending on view chosen by user
        if (currentFragment == MAP_TEXT_FRAGMENTS) {
            showMapTextFragments();
        } else if (currentFragment == LIST_FRAGMENT) {
            showListFragment();
        }

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // display correct Fragment(s) depending on view chosen by user
                if (currentFragment == MAP_TEXT_FRAGMENTS) {
                    showListFragment();
                    currentFragment = LIST_FRAGMENT;
                } else {
                    showMapTextFragments();
                    currentFragment = MAP_TEXT_FRAGMENTS;
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    /**
     * method called by the BlankFragment when it's created, to hide the Floating Action Button
     */
    public void fabHide() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.hide();
    }

    /**
     * method called by the BlankFragment when it's destroyed, to show the Floating Action Button
     */
    public void fabShow() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.show();
    }

    private void breakPhotoConstraints(ConstraintSet set) {
        set.clear(R.id.map2, ConstraintSet.BOTTOM);
        set.clear(R.id.text, ConstraintSet.TOP);
        set.clear(R.id.map2, ConstraintSet.END);
        set.clear(R.id.text, ConstraintSet.START);
    }

    /**
     * obtain a reference to the fragment_text instance and call the changeText() method on the object
     *
     * @param marker The marker coming from the map fragment
     */
    public boolean onMarkerClick(Marker marker) {

        // the key for the HashMap is the Marker Title
        String key = marker.getTitle();
        // call the method to update the text fragment using the key
        if(key != null) {
            updateTextFragment(key);
        }

        return true;
    }

    public void showMapTextFragments() {
        // hide the ListFragment and show the map and text fragments
        fragmentManager.beginTransaction()
                .hide(listFragment)
                .show(mapsFragment)
                .show(textFragment)
                .commitNow();

        // show the map overlay with the marker titles
        floatingMarkerTitlesOverlay.setVisibility(View.VISIBLE);

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

        // hide the List and map Fragments and show the list fragment
        fragmentManager.beginTransaction()
                .hide(mapsFragment)
                .hide(textFragment)
                .show(listFragment)
                .commitNow();

        // hide the map overlay with the marker titles
        floatingMarkerTitlesOverlay.setVisibility(View.INVISIBLE);
    }

    public Map<String, PointOfInterest> getPointsOfInterest() {
        return pointsOfInterest;
    }

    public PointOfInterest getPointOfInterest(String key) {
        return pointsOfInterest.get(key);
    }

    public void setCurrentPointOfInterest(String currentPointOfInterestKey) {
    }

    private void parseXML() {

        String fileName;
        String languagename = Locale.getDefault().getDisplayLanguage();
        switch (languagename) {
            case "English":
                fileName = "pointsofinterest.xml";
                break;
            case "Deutsch":
                fileName = "pointsofinterest_DE.xml";
                break;
            default:
                fileName = "pointsofinterest.xml";
        }

        XmlPullParserFactory parseFactory;
        try {
            parseFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parseFactory.newPullParser();
            InputStream inputStream = getAssets().open(fileName);
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(inputStream, null);

            processParsing(parser);
        } catch (XmlPullParserException | IOException ignored) { }
    }

    private void processParsing(XmlPullParser parser) throws IOException, XmlPullParserException {
        int eventType = parser.getEventType();
        PointOfInterest currentPointOfInterest = null;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String eltName;

            if (eventType == XmlPullParser.START_TAG) {
                eltName = parser.getName();

                if (eltName.equals("pointofinterest")) {
                    currentPointOfInterest = new PointOfInterest();
                } else if (currentPointOfInterest != null) {
                    switch (eltName) {
                        case "key":
                            pointsOfInterest.put(parser.nextText(), currentPointOfInterest);
                            break;
                        case "id":
                            currentPointOfInterest.setId(Integer.parseInt(parser.nextText()));
                            break;
                        case "latitude":
                            currentPointOfInterest.setLatitude(Double.parseDouble(parser.nextText()));
                            break;
                        case "longitude":
                            currentPointOfInterest.setLongitude(Double.parseDouble(parser.nextText()));
                            break;
                        case "placetitle":
                            currentPointOfInterest.setPlaceTitle(parser.nextText());
                            break;
                        case "placedescription":
                            currentPointOfInterest.setPlaceDescription(parser.nextText());
                            break;
                        case "placephoto":
                            currentPointOfInterest.setPlacePhoto(parser.nextText());
                            break;
                    }
                }
            }
            eventType = parser.next();
        }
    }

    public int getDrawableIdentifier(Context context, String name) {
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }

    public Drawable getPhoto(String photoName) {
        @SuppressLint("UseCompatLoadingForDrawables") Drawable drawable = getResources().getDrawable(getDrawableIdentifier(this, photoName), null);
        return drawable;
    }

    public void showOnMap(String titleText) {

        Log.i(TAG, "TitleText = " + titleText);
        showMapTextFragments();
        // TODO try converting variable type above to MapsFragment from Fragment
        MapsFragment mapsFragment2 = (MapsFragment) getSupportFragmentManager().findFragmentByTag("map_tag");


        PointOfInterest poi = getPointOfInterest(titleText);

        // get the latitude and longitude from the PointOfInterest object, and call a method to zoom in on that point
        double latitude = poi.getLatitude();
        double longitude = poi.getLongitude();
        int zoom = 16;
        mapsFragment2.zoomToPlace(latitude, longitude, zoom);

        // call a method to update the text fragment using the titleText as the key
        updateTextFragment(titleText);

    }

    public void updateTextFragment(String key) {
        // get the PointOfInterest object from the HashMap using the key
        PointOfInterest poi = getPointOfInterest(key);

        // get the text and photo from the PointOfInterest object
        String newTitleText = poi.getPlaceTitle();
        String newDescriptionText = poi.getPlaceDescription();
        ArrayList<String> photos = poi.getPlacePhotos();

        TextFragment textFragment = (TextFragment) getSupportFragmentManager().findFragmentById(R.id.text);

        // clear text and photos
        assert textFragment != null;
        textFragment.clearTextFragment();

        // Set the text in the Text fragment
        textFragment.addText(newTitleText, 20, true);
        textFragment.addText(newDescriptionText, 14, false);

        // Set the photo(s) in the Text fragment
        for (int i = 0; i < photos.size(); i++) {
            @SuppressLint("UseCompatLoadingForDrawables") Drawable newImage = getResources().getDrawable(getDrawableIdentifier(this, photos.get(i)), null);
            textFragment.addPhoto(newImage);
        }
    }

}