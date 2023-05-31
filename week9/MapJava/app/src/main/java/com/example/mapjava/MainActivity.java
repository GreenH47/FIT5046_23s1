package com.example.mapjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;

public class MainActivity extends AppCompatActivity {
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hardcoded coordinates for Monash Caulfield
        final Point point = Point.fromLngLat(145.045837, -37.876823);

        // Initialize the MapView and set the camera position
        mapView = findViewById(R.id.mapView);
        CameraOptions cameraPosition = new CameraOptions.Builder()
                .zoom(13.0)
                .center(point)
                .build();

        // Load the map style and set the camera position
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS);
        mapView.getMapboxMap().setCamera(cameraPosition);
    }
}
