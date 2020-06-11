package com.example.findmypet;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ArrayList<Pet> LPM;
    ControladorBD controlador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        controlador= new ControladorBD(getApplicationContext());
        LPM = controlador.obtenerRegistros();
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
        for(Pet pet:LPM){
            LatLng marcador = new LatLng(pet.getLatitud(), pet.getLongitud());
            mMap.addMarker(new MarkerOptions().position(marcador).title(pet.getNombre()).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marcador_foreground)));
        }
        // Add a marker in Sydney and move the camera
        LatLng vista=new LatLng(10.9878,-74.7889);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vista,8));
    }
}