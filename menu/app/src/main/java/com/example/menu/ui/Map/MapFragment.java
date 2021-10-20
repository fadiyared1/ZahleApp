package com.example.menu.ui.Map;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.menu.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener, GoogleMap.OnPolylineClickListener{

    private GoogleMap mMap;
    private Button btn;
    View root;
    Button btnHiking, btnBiking, btnOffroad;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getActivity().setContentView(R.layout.activity_maps);
        root = inflater.inflate(R.layout.activity_maps, container, false);
        btnHiking = root.findViewById(R.id.hiking);
        btnBiking = root.findViewById(R.id.biking);
        btnOffroad = root.findViewById(R.id.offroad);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnHiking.setOnClickListener(this);
        btnBiking.setOnClickListener(this);
        btnOffroad.setOnClickListener(this);
        return root;

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng zahle = new LatLng(33.84675, 35.90203);
        mMap.addMarker(new MarkerOptions().position(zahle).title("Marker in zahle").icon(BitmapFromVector(getContext(), R.drawable.ic_baseline_my_location_24)));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(zahle, 12));
        // Set listeners for click events.
        googleMap.setOnPolylineClickListener(this);


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
    public void onClick(View v) {
        int colora= getResources().getColor(R.color.mypurple);
        int colort= getResources().getColor(R.color.white);
        switch (v.getId()) {
            case R.id.hiking:
                Toast.makeText(getContext(), "Hiking", Toast.LENGTH_SHORT).show();
                drawHikingPolylines(mMap);
                break;
            case R.id.biking:
                Toast.makeText(getContext(), "Biking", Toast.LENGTH_SHORT).show();
                drawBikingPolylines(mMap);
                break;
            case R.id.offroad:
                Toast.makeText(getContext(), "Offroad", Toast.LENGTH_SHORT).show();
                drawOffroadPolylines(mMap);
                break;

        }

    }

    public void drawHikingPolylines(GoogleMap googleMap) {
        mMap.clear();

        mMap = googleMap;
        LatLng sydney = new LatLng(33.84675, 35.90203);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").icon(BitmapFromVector(getContext(), R.drawable.ic_baseline_my_location_24)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .color(Color.MAGENTA)
                .add(
                        new LatLng(33.863687, 35.951299),
                        new LatLng(33.863057, 35.952065),
                        new LatLng(33.862120, 35.951030),
                        new LatLng(33.863554, 35.949882)));


        Polyline polyline2 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .color(Color.MAGENTA)
                .add(
                        new LatLng(33.864072, 35.954757),
                        new LatLng(33.864412, 35.954699),
                        new LatLng(33.864854, 35.954697),
                        new LatLng(33.865464, 35.955619),
                        new LatLng(33.863189, 35.957808),
                        new LatLng(33.864971, 35.962216)));

        Polyline polyline3 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .color(Color.MAGENTA)
                .add(
                        new LatLng(33.886642, 35.982750),
                        new LatLng(33.885305, 35.984095),
                        new LatLng(33.883586, 35.982711),
                        new LatLng(33.882530, 35.988552),
                        new LatLng(33.879597, 35.984586),
                        new LatLng(33.871820, 35.988656)));

        Polyline polyline4 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .color(Color.MAGENTA)
                .add(
                        new LatLng(33.862081, 35.885130),
                        new LatLng(33.865947, 35.881109),
                        new LatLng(33.866646, 35.882798)));

        Polyline polyline5 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .color(Color.MAGENTA)
                .add(
                        new LatLng(33.857270,  35.883243),
                        new LatLng(33.857616, 35.882834),
                        new LatLng(33.856324,  35.882652),
                        new LatLng(33.856857, 35.882191)));

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(33.88675, 35.94203), 12));

    }

    public void drawOffroadPolylines(GoogleMap googleMap) {
        mMap.clear();

        mMap = googleMap;
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").icon(BitmapFromVector(getContext(), R.drawable.ic_baseline_my_location_24)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));//hay awal ma teftah l camera

        Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .color(Color.GREEN)
                .add(
                        new LatLng(33.853612, 35.931225),
                        new LatLng(33.868742, 35.965235),
                        new LatLng(33.870255, 36.015037),
                        new LatLng(33.925202, 36.081236)));


        Polyline polyline2 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .color(Color.GREEN)
                .add(
                        new LatLng(33.759749, 35.910576),
                        new LatLng(33.800637, 35.952178),
                        new LatLng(33.778429, 35.979204),
                        new LatLng(33.808704, 36.020307)));
        Polyline polyline3 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .color(Color.GREEN)
                .add(
                        new LatLng(33.852684, 35.883400),
                        new LatLng(33.851147, 35.883682),
                        new LatLng(33.850678, 35.882667)));


        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(33.88675, 35.94203), 10));

    }

    public void drawBikingPolylines(GoogleMap googleMap) {
        mMap.clear();

        mMap = googleMap;
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").icon(BitmapFromVector(getContext(), R.drawable.ic_baseline_my_location_24)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .color(Color.BLUE)
                .add(
                        new LatLng(33.863687, 35.951299),
                        new LatLng(33.863057, 35.952065),
                        new LatLng(33.862120, 35.951030),
                        new LatLng(33.863554, 35.949882)));


        Polyline polyline2 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .color(Color.BLUE)
                .add(
                        new LatLng(33.864072, 35.954757),
                        new LatLng(33.864412, 35.954699),
                        new LatLng(33.864854, 35.954697),
                        new LatLng(33.865464, 35.955619),
                        new LatLng(33.863189, 35.957808),
                        new LatLng(33.864971, 35.962216)));

        Polyline polyline3 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .color(Color.BLUE)
                .add(
                        new LatLng(33.886642, 35.982750),
                        new LatLng(33.885305, 35.984095),
                        new LatLng(33.883586, 35.982711),
                        new LatLng(33.882530, 35.988552),
                        new LatLng(33.879597, 35.984586),
                        new LatLng(33.871820, 35.988656)));
        Polyline polyline4 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .color(Color.BLUE)
                .add(
                        new LatLng(33.862081, 35.885130),
                        new LatLng(33.865947, 35.881109),
                        new LatLng(33.866646, 35.882798)));

        Polyline polyline5 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .color(Color.BLUE)
                .add(
                        new LatLng(33.857270,  35.883243),
                        new LatLng(33.857616, 35.882834),
                        new LatLng(33.856324,  35.882652),
                        new LatLng(33.856857, 35.882191)));

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(33.88675, 35.94203), 12));

    }

    @Override
    public void onPolylineClick(@NonNull Polyline polyline) {

        Intent intent=new Intent(getContext(), polylineInfo.class);
        String id = polyline.getId();
        startActivity(intent);
        //Toast.makeText(getContext(), id, Toast.LENGTH_LONG).show();

    }
}