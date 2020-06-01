package com.example.tyler.shred_v2;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapsActivity.this);
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
    public void onMapReady(GoogleMap googleMap){
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        final LatLng schweitzer = new LatLng(48.3640323,-116.6308583);
        LatLng silver = new LatLng(47.4989228,-116.1211706);
        LatLng lookOut = new LatLng(47.4560276,-115.6994406);
        LatLng _49N = new LatLng(48.3011007,-117.5651048);
        LatLng mtSpokane = new LatLng(47.9213856,-117.0986047);
        //LatLngBounds center = new LatLngBounds(_49N, lookOut);
        LatLng center = new LatLng(47.9082,-116.623);

        final Marker schweitzerMarker = mMap.addMarker(new MarkerOptions()
                .position(schweitzer)
                .title("Schweitzer Mountain Resort")
                .snippet("Sandpoint, ID")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.schweitzermarker)));

        final Marker silverMarker = mMap.addMarker(new MarkerOptions()
                .position(silver)
                .title("Silver Mountain")
                .snippet("Kellogg, ID")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.silvermarker)));

        final Marker lookOutMarker = mMap.addMarker(new MarkerOptions()
                .position(lookOut)
                .title("Lookout Pass Ski & Recreation Area")
                .snippet("Mullan, ID")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.lookoutmarker)));

        final Marker _49NMarker = mMap.addMarker(new MarkerOptions()
                .position(_49N)
                .title("49"+ (char) 0x00B0 + " North Mountain Resort")
                .snippet("Chewelah, WA")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.n49marker)));

        final Marker mtSpokaneMarker = mMap.addMarker(new MarkerOptions()
                .position(mtSpokane)
                .title("Mt. Spokane Ski & Snowboard Park")
                .snippet("Mead, WA")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mtspokanemarker)));

        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center.getCenter(), 8));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(center));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(8));
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                if(marker.equals(schweitzerMarker)){
                    Intent intent = new Intent(MapsActivity.this, schweitzerActivity.class);
                    startActivity(intent);
                }
                if(marker.equals(mtSpokaneMarker)){
                    Intent intent = new Intent(MapsActivity.this, mtSpokaneActivity.class);
                    startActivity(intent);
                }
                if(marker.equals(lookOutMarker)){
                    Intent intent = new Intent(MapsActivity.this, lookOutActivity.class);
                    startActivity(intent);
                }
                if(marker.equals(_49NMarker)){
                    Intent intent = new Intent(MapsActivity.this, _49NActivity.class);
                    startActivity(intent);
                }
                if(marker.equals(silverMarker)){
                    Intent intent = new Intent(MapsActivity.this, silverActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
