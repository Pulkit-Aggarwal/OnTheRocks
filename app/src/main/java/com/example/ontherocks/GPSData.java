package com.example.ontherocks;

import java.util.ArrayList;

public class GPSData {
    double Latitude;
    double Longitude;

    public GPSData(double Latitude, double longitude) {
        this.Latitude = Latitude;
        this.Longitude = longitude;
    }


    public static double distance(GPSData a, GPSData b) {
        double lat1 = Math.toRadians(a.Latitude);
        double lat2 = Math.toRadians(b.Latitude);
        double lon1 = Math.toRadians(a.Longitude);
        double lon2 = Math.toRadians(b.Longitude);

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double dist = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(dist));

        // for Kilometers. For miles use 3956
        double r = 6371;

        return(c * r);
    }


    @Override
    public String toString() {
        return "Latitude: " + Latitude + " " + "Longitude" + Longitude;
    }
}
