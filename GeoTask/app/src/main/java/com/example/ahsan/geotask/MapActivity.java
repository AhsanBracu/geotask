package com.example.ahsan.geotask;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, SearchView.OnQueryTextListener {

    MenuItem btnsubmit;
    Button btn;
    public GoogleMap googleMap;
    String location;
    LatLng position=new LatLng(10.0,10.0);
    SearchView searchView;
    Circle circle;
    Spinner spinner;
    MarkerOptions marker;
    Button done;
    double radius;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SupportMapFragment map = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //searchView= (SearchView)MenuItemCompat.getActionView(searchItem);
       // searchView.setOnQueryTextListener(this);
        spinner=(Spinner)findViewById(R.id.radius_spinner);
        done=(Button)findViewById(R.id.done);
        //String radius=spinner.getSelectedItem().toString();
        map.getMapAsync(this);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                double latitude=position.latitude;
                double longitude=position.longitude;
                Toast.makeText(getApplicationContext(),"Lat "+latitude+"longitude "+longitude,Toast.LENGTH_LONG).show();
                intent.putExtra("Latitude",latitude);
                intent.putExtra("Longitude",longitude);
                setResult(200, intent);
                finish();

            }
        });


        //map.getMapAsync(new OnMapReadyCallback() {

          //  @Override
          //  public void onMapReady(GoogleMap googleMap) {
                //googleMap.getUiSettings().setZoomControlsEnabled(true);
                // LatLng sydney = new LatLng(-34, 151);
                // googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
                // googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
               /* if (location != null) {
                    position = getLocationFromAddress(location);
                    //LatLng sydney = new LatLng(l.latitude, lng.longitude);
                    googleMap.addMarker(new MarkerOptions().position(position).title("Marker in Any"));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(position));
                    CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
                    googleMap.animateCamera(zoom);
                    double r = Double.parseDouble(spinner.getSelectedItem().toString());
                    Toast.makeText(getApplicationContext(), "nothing: " + location, Toast.LENGTH_LONG).show();
                    if (circle != null) {
                        circle.remove();
                    }
                    circle = googleMap.addCircle(new CircleOptions()
                            .center(position)
                            .radius(r)
                            .strokeColor(Color.RED));

                }*/
          //  }

       // });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.map_menu, menu);
        btnsubmit = menu.findItem(R.id.button_bar);
        MenuItem searchItem=menu.findItem(R.id.action_search);
        searchView= (SearchView)MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        btn = (Button) MenuItemCompat.getActionView(btnsubmit);
        btnsubmit.setVisible(false);
        MenuItemCompat.collapseActionView(searchItem);
        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                btnsubmit.setVisible(true);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                btnsubmit.setVisible(false);
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {

           /* case R.id.menu_help:
                Toast.makeText(getApplicationContext(), "action_Bar", Toast.LENGTH_LONG).show();
                break;
*/
            case R.id.button_bar:
                Intent intent=new Intent();
                double latitude=position.latitude;
                double longitude=position.longitude;
                Toast.makeText(getApplicationContext(),"Lat "+latitude+"longitude "+longitude,Toast.LENGTH_LONG).show();
                intent.putExtra("Latitude",latitude);
                intent.putExtra("Longitude",longitude);
                setResult(200, intent);
                finish();
                return  true;

        }
        return super.onOptionsItemSelected(item);
    }

    public LatLng getLocationFromAddress(String strAddress) {
        Geocoder geocoder = new Geocoder(getApplicationContext());
        LatLng latLng = null;
        try {
            List<Address> list = geocoder.getFromLocationName(strAddress, 1);
            Address address = list.get(0);

            double lat = address.getLatitude();
            double lng = address.getLongitude();
            latLng = new LatLng(lat, lng);
          //  googleMap.addMarker(new MarkerOptions().position(latLng).title("Marker in Sydney"));
           // googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

            return new LatLng(lat, lng);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return latLng;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap=googleMap;
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        LatLng sydney = new LatLng(23.7000, 90.3667);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in dhaka"));
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 12.0f));
        //CameraUpdate zoom = CameraUpdateFactory.zoomTo(10);
        //googleMap.animateCamera(zoom);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.e("query ",query);
        position = getLocationFromAddress(query);
        Log.e("position "," "+position);
        //LatLng sydney = new LatLng(l.latitude, lng.longitude);
        /// if(googleMap!=null) {
        if(marker!=null)
            marker=null;
        marker=new MarkerOptions().position(position).title("Marker in Any");
        googleMap.addMarker(marker);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 16.0f));
        //CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
        //googleMap.animateCamera(zoom);
        double r = Double.parseDouble(spinner.getSelectedItem().toString());
        Toast.makeText(getApplicationContext(), "nothing: " + query, Toast.LENGTH_LONG).show();
        if (circle != null) {
            circle.remove();
        }
        circle = googleMap.addCircle(new CircleOptions()
                .center(position)
                .radius(r)
                .strokeColor(Color.RED));
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
