package com.furminger.allaboutclovelly.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.furminger.allaboutclovelly.MainActivity;
import com.furminger.allaboutclovelly.PointOfInterest;
import com.furminger.allaboutclovelly.R;
import com.furminger.allaboutclovelly.databinding.FragmentMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Iterator;
import java.util.Map;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private static final int LOCATION_REQUEST_CODE = 101;

    private GoogleMap mMap;
    private @NonNull
    FragmentMapsBinding binding;
    private final String TAG = "mapDemo";

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         *
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {

            mMap = googleMap;

            // following code is to update location to current location, provided user has
            // given permission to do so.
            if(mMap != null) {
                Context context = getActivity().getApplicationContext();
                int permission = ContextCompat.checkSelfPermission(context,
                        Manifest.permission.ACCESS_FINE_LOCATION);

                if(permission == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);
                } else {
                    requestPermission(Manifest.permission.ACCESS_FINE_LOCATION,
                            LOCATION_REQUEST_CODE);
                }
            }

            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

            // add all the other markers
            addAllMarkers();

            // display user controls i.e., zoom in and out buttons, my location button etc
            UiSettings mapSettings;
            mapSettings = mMap.getUiSettings();
            mapSettings.setZoomControlsEnabled(true);
            mapSettings.setScrollGesturesEnabled(true);
            mapSettings.setTiltGesturesEnabled(true);
            mapSettings.setRotateGesturesEnabled(true);
            mapSettings.setMyLocationButtonEnabled(true);

            mMap.setPadding(0, 150, 150, 150);

            // zoom in on the centre of Clovelly
            LatLng clovelly = new LatLng(50.998128, -4.399118);
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(clovelly)
                    .zoom(19)
                    .build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            // listener for markers
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    markerClicked(marker);
                    return true;
                }
            });
        }

        protected void requestPermission(String permissionType, int requestCode) {
            Activity activity = getActivity();
            ActivityCompat.requestPermissions(activity, new String[]{permissionType}, requestCode);
        }


    /**
     * set up a listener that will allow the fragment to call the MainActivity when the marker
     * is clicked
     */
    GoogleMap.OnMarkerClickListener activityCallback;

    public interface OnMarkerClickListener {
        public void onMarkerClick(Marker marker);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            activityCallback = (GoogleMap.OnMarkerClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + " must implement OnMarkerClickListener");
        }


    }

    /**
     * this method is called when the marker is clicked by the user
     */
    public void markerClicked(Marker marker) {
        activityCallback.onMarkerClick(marker);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMapsBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);

        }
    }

    private void addAllMarkers() {

        for(PointOfInterest pointOfInterest : ((MainActivity) getActivity()).getPointsOfInterest().values()) {
            addPointOfInterestMarker(pointOfInterest);
        }
    }

    /**
     * Method to add a marker to the map. Takes in a pointOfInterest object
     * @param pointOfInterest
     */
    public void addPointOfInterestMarker(PointOfInterest pointOfInterest) {

        double latitude = pointOfInterest.getLatitude();
        double longitude = pointOfInterest.getLongitude();
        String placeTitle = pointOfInterest.getPlaceTitle();
        String placeDescription = pointOfInterest.getPlaceDescription();

        LatLng position = new LatLng(latitude, longitude);

        mMap.addMarker(new MarkerOptions()
                .position(position)
                .title(placeTitle)
                .snippet(placeDescription));
    }

}