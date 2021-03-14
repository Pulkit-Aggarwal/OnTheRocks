package com.example.ontherocks;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.NumberFormat;
import java.util.Locale;

import static android.Manifest.*;

public class MainActivity extends AppCompatActivity {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public static CO2data co2Calc = new CO2data();
    private static final int GPS_TIME_INTERVAL = 1000;
    private static final int HANDLER_DELAY = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final TextView distance = findViewById(R.id.distance);
        final TextView co2 = findViewById(R.id.co2);
        final TextView cost = findViewById(R.id.cost);

        final LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        final locationHandler handler = new locationHandler();
        final TextView tv = (TextView) findViewById(R.id.textView2);

        final Location[] myLocation = new Location[1];
        final Handler handler2 = new Handler(Looper.getMainLooper());

        handler2.postDelayed(new Runnable() {
            public void run() {
                myLocation[0] = obtainLocation(locationManager, handler);
                if (myLocation[0] == null) {
                    tv.setText("It was null");
                } else {
                    //The construction of GPSData object actually updates totalDistance
                    GPSData data = new GPSData(myLocation[0].getLatitude(), myLocation[0].getLongitude());
                    //GPSData.increment();
                    //String incrementVal = String.valueOf(GPSData.getTotalDistance());

                    //Figure out distance
                    String coord = data.toString();
                    double totalDistance = GPSData.getTotalDistance();
                    String totalDistanceStr = String.valueOf(totalDistance);

                    //Figure out co2 using distance
                    double co2Emission = CO2data.carEmission(totalDistance);
                    String co2String = String.valueOf(co2Emission);

                    //Figure out cost using co2
                    double co2Cost = CO2data.cost(co2Emission);
                    Locale locale = new Locale("en", "GB");
                    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
                    //BigDecimal bd = new BigDecimal(co2Cost);
                    //bd = bd.round(new MathContext(2));
                    String costString = numberFormat.format(co2Cost);
                    String distanceStr = coord + "\n" + totalDistanceStr + " km";
                    String co2Str = co2String + " kg";

                    distance.setText(distanceStr);
                    co2.setText(co2Str);
                    cost.setText(costString);

                }
                handler2.postDelayed(this, HANDLER_DELAY);
            }
        }, HANDLER_DELAY);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Location obtainLocation(LocationManager locationManager, locationHandler handler) {
        if (ActivityCompat.checkSelfPermission(this, permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{permission.ACCESS_FINE_LOCATION, permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
            //onRequestPermissionsResult(MY_PERMISSIONS_REQUEST_LOCATION, new String[]{permission.ACCESS_FINE_LOCATION, permission.ACCESS_COARSE_LOCATION} );
        }
        Location test = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (true) { // I'm not a coward
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        GPS_TIME_INTERVAL, 0, handler);
            }
        return test;
        }
}
