package com.example.pj0322;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap gMap;
    SupportMapFragment mapFragment;
    GroundOverlayOptions videoMark;
    ArrayList<GroundOverlayOptions> markArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Google Map API");

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap = googleMap;
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.498095, 127.027610), 15));
        gMap.getUiSettings().setZoomControlsEnabled(true);
        gMap.setOnMapClickListener(latLng -> {
            videoMark = new GroundOverlayOptions().image(BitmapDescriptorFactory
                    .fromResource(R.drawable.presence_video_busy))
                    .position(latLng, 100f, 100f);
            markArray.add(videoMark);
            gMap.clear();
            for (int i=0; i<markArray.size(); i++) {
                gMap.addGroundOverlay(markArray.get(i));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, "위성 지도");
        menu.add(0, 2, 0, "일반 지도");
        menu.add(0, 3, 0, "바로전 CCTV 지우기");
        menu.add(0, 4, 0, "모든 CCTV 지우기");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 1: gMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); return true;
            case 2: gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); return true;
            case 3:
                gMap.clear();
                markArray.remove(markArray.size()-1);
                for (int i=0; i<markArray.size(); i++) {
                    gMap.addGroundOverlay(markArray.get(i));
                }
                return true;
            case 4:
                gMap.clear();
                markArray.clear();
                return true;
        }
        return false;
    }
}