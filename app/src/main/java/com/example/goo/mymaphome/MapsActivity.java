package com.example.goo.mymaphome;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng pt1,pt2,pt3,pt4;
    private Marker mpt1,mpt2,mpt3,mpt4;
    private LatLngBounds bounds;

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
        pt1 = new LatLng(18.234343,99.487447);
        pt2 = new LatLng(18.235877,99.487900);
        pt3 = new LatLng(18.235069,99.486182);
        pt4 = new LatLng(18.233885,99.484238);

        mpt1 = mMap.addMarker(new MarkerOptions()
                .position(pt1)
                .title("LPRU")
                .snippet("มหาวิทยาลัยราชภัฏลำปาง")
                .icon(BitmapDescriptorFactory
                    .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        mpt2 = mMap.addMarker(new MarkerOptions()
                .position(pt2)
                .title("EDU")
                .snippet("คณะครุศาสตร์")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        mpt3 = mMap.addMarker(new MarkerOptions()
                .position(pt3)
                .title("Comcentre")
                .snippet("ศูนย์คอม")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));

        mpt4 = mMap.addMarker(new MarkerOptions()
                .position(pt4)
                .title("Management")
                .snippet("คณะวิทยาการจัดการ")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        bounds = new LatLngBounds.Builder()
                .include(pt1)
                .include(pt2)
                .include(pt3)
                .include(pt4)
                .build();
        final View mapview = getSupportFragmentManager()
                .findFragmentById(R.id.map).getView();
        if (mapview.getViewTreeObserver().isAlive()){
             mapview.getViewTreeObserver()
                    .addOnGlobalLayoutListener(
                            new ViewTreeObserver.OnGlobalLayoutListener() {
                                @SuppressWarnings("deprecation")
                                @SuppressLint("New API")
                                @Override
                                public void onGlobalLayout() {
                                    LatLngBounds bounds = new LatLngBounds.Builder()
                                            .include(pt1)
                                            .include(pt2)
                                            .include(pt3)
                                            .include(pt4)
                                            .build();
                                if (Build.VERSION.SDK_INT<Build.VERSION_CODES
                                        .JELLY_BEAN){
                                    mapview.getViewTreeObserver()
                                            .addOnGlobalLayoutListener(this);
                                }else{
                                    mapview.getViewTreeObserver()
                                            .removeOnGlobalLayoutListener(this);
                                }
                                mMap.moveCamera(
                                        CameraUpdateFactory.newLatLngBounds(bounds,40)
                                );
                                }
                            }
                    );
        }


//        // Add a marker in Sydney and move the camera
//        LatLng lpru = new LatLng(18.234363, 99.488070);
//        mMap.addMarker(new MarkerOptions()
//                .position(lpru)
//                .title("Marker in LPRU")
//                .snippet("มหาวิทยาลัยราชภัฏลำปาง")
////                .icon(BitmapDescriptorFactory
//                    //    .defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placeholder))
//        );
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(lpru));
////        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lpru,17));
//        CameraPosition cameraPosition = new CameraPosition.Builder()
//                .target(lpru)
//                .zoom(17)
//                .tilt(60)
//                .build();
//        mMap.animateCamera(CameraUpdateFactory
//                .newCameraPosition(cameraPosition));
    }
}
