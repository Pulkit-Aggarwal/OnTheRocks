package com.example.ontherocks;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;


public class locationHandler implements LocationListener {

    private LocationManager manager;

    private void getLocation() {

    }

    // We might need to use this.
    @Override
    public void onLocationChanged(Location loc) {
        // update location
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
