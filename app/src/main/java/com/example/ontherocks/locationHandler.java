package com.example.ontherocks;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;
import static android.content.Context.LOCATION_SERVICE;

public class locationHandler implements LocationListener {

    private Context Context;

    // We might need to use this.
    @Override
    public void onLocationChanged(Location loc) {

    }

    // Unused
    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {}

    // Unused
    @Override
    public void onProviderEnabled(String s) {}

    // Unused
    @Override
    public void onProviderDisabled(String s) {}
}