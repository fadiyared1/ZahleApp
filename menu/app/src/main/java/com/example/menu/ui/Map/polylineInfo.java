package com.example.menu.ui.Map;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;


import java.util.ArrayList;
import java.util.List;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.menu.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class polylineInfo extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener ,GoogleMap.OnPolylineClickListener{

    TextView rateCount; //showRate;
    // EditText review;
    Button submit;
    float rateValue;
    MapView rosterMapView;

    TextView descText;
    ImageButton show, hide;
    GoogleMap mMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.polylineinfo);



        /*rosterMapView = (MapView) findViewById(R.id.mapView);
        rosterMapView.onCreate(savedInstanceState);*/
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);

        mapFragment.getMapAsync(this);

        int pt1 = getResources().getIdentifier("hike1", "drawable", getPackageName());
        int pt2 = getResources().getIdentifier("hike2", "drawable", getPackageName());
        int pt3 = getResources().getIdentifier("hike3", "drawable", getPackageName());
        ImageSlider imageSlider = findViewById(R.id.slider);
        List<SlideModel> slideModel = new ArrayList<>();
        slideModel.add(new SlideModel(pt1));
        slideModel.add(new SlideModel(pt2));
        slideModel.add(new SlideModel(pt3));

        imageSlider.setImageList(slideModel, true);

        /*TextView textViewName = findViewById(R.id.restauName);
        textViewName.setText("Name Example");*/


        descText = (TextView) findViewById(R.id.description_text);
        show = (ImageButton) findViewById(R.id.show);
        show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Show button");
                show.setVisibility(View.INVISIBLE);
                hide.setVisibility(View.VISIBLE);
                descText.setMaxLines(Integer.MAX_VALUE);

            }
        });
        hide = (ImageButton) findViewById(R.id.hide);
        hide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Hide button");
                hide.setVisibility(View.INVISIBLE);
                show.setVisibility(View.VISIBLE);
                descText.setMaxLines(5);

            }
        });


    }

    private BitmapDescriptor BitmapFromVector(Context context, int vectorResId) {
        // below line is use to generate a drawable.
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        // below line is use to set bounds to our vector drawable.
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        // below line is use to create a bitmap for our
        // drawable which we have added.
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        // below line is use to add bitmap in our canvas.
        Canvas canvas = new Canvas(bitmap);
        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas);
        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap=googleMap;
        LatLng zahle = new LatLng(33.84675, 35.90203);
        googleMap.addMarker(new MarkerOptions().position(zahle).title("Marker in zahle").icon(BitmapFromVector(getApplicationContext(), R.drawable.ic_baseline_my_location_24)));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(zahle, 12));
        Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .color(Color.MAGENTA)
                .add(
                        new LatLng(33.863687, 35.951299),
                        new LatLng(33.863057, 35.952065),
                        new LatLng(33.862120, 35.951030),
                        new LatLng(33.863554, 35.949882)));
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPolylineClick(@NonNull Polyline polyline) {

    }
}
