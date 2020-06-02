package com.kiwi.waiterly.vista.mapa;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kiwi.waiterly.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);

        // Add a marker in Sydney and move the camera
        LatLng barcelona = new LatLng(41.3887901, 2.1589899);
        LatLng marcador1 = new LatLng(41.379564, 2.167203);
        LatLng marcador2 = new LatLng(41.394327, 2.191843);
        LatLng marcador3 = new LatLng(41.398121, 2.199290);
        LatLng marcador4 = new LatLng(41.386235, 2.146300);
        LatLng marcador5 = new LatLng(41.395933, 2.136832);
        LatLng marcador6 = new LatLng(41.441696, 2.237285);
        LatLng marcador7 = new LatLng(41.457493, 2.255982);
        LatLng marcador8 = new LatLng(41.316202, 2.028248);
        LatLng marcador9 = new LatLng(41.333813, 2.035098);
        LatLng marcador10 = new LatLng(41.357954, 2.061158);


        mMap.addMarker(new MarkerOptions().position(marcador1).title("Los amigos"));
        mMap.addMarker(new MarkerOptions().position(marcador2).title("El dorado"));
        mMap.addMarker(new MarkerOptions().position(marcador3).title("Juan y Luca"));
        mMap.addMarker(new MarkerOptions().position(marcador4).title("Durums"));
        mMap.addMarker(new MarkerOptions().position(marcador5).title("Restaurante Jose Fina"));
        mMap.addMarker(new MarkerOptions().position(marcador6).title("Los Hermanos"));
        mMap.addMarker(new MarkerOptions().position(marcador7).title("Paella para todos"));
        mMap.addMarker(new MarkerOptions().position(marcador8).title("Señor Pollo"));
        mMap.addMarker(new MarkerOptions().position(marcador9).title("Restaurante Boliviano"));
        mMap.addMarker(new MarkerOptions().position(marcador10).title("Laguna Alalay"));

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(barcelona,12), 4000,null);
        mMap.setMaxZoomPreference(1000);
    }
}
