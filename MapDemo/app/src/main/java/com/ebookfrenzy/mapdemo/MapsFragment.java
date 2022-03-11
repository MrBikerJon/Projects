package com.ebookfrenzy.mapdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ebookfrenzy.mapdemo.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment {

    private static final int LOCATION_REQUEST_CODE = 101;

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
//            LatLng sydney = new LatLng(-34, 151);
//            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

            mMap = googleMap;

            // Add a marker in Sydney and move the camera
            LatLng clovelly = new LatLng(50.998128, -4.399118);
            mMap.addMarker(new MarkerOptions().position(clovelly).title("Marker in Clovelly"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(clovelly));
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

            // display user controls i.e., zoom in and out buttons, my location button etc
            UiSettings mapSettings;
            mapSettings = mMap.getUiSettings();
            mapSettings.setZoomControlsEnabled(true);
            mapSettings.setScrollGesturesEnabled(true);
            mapSettings.setTiltGesturesEnabled(true);
            mapSettings.setRotateGesturesEnabled(true);


            // create map marker
            // TODO move to separate method/class, with locations stored in a file/array
            LatLng position = new LatLng(50.99795648517228, -4.399230743006255);
            Marker fishermansCottageLocation = mMap.addMarker(new MarkerOptions()
                    .position(position).title("Fisherman's Cottage").snippet("Some text here"));


            // zoom in on the map
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(clovelly)
                    .zoom(19)
                    .build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


            // following code is to update location to current location, provided user has
            // given permission to do so. Commented out for now as need location to continue to show
            // Clovelly
//        if(mMap != null) {
//            int permission = ContextCompat.checkSelfPermission(this,
//                    Manifest.permission.ACCESS_FINE_LOCATION);
//
//            if(permission == PackageManager.PERMISSION_GRANTED) {
//                mMap.setMyLocationEnabled(true);
//            } else {
//                requestPermission(Manifest.permission.ACCESS_FINE_LOCATION,
//                        LOCATION_REQUEST_CODE);
//            }
//        }

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}