package com.example.ontherocks;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Class used to represent GPS data.
 * Stores the latitude and longitude.
 */
public class GPSData {
    private double Latitude;
    private double Longitude;
    private long timeStamp;

    private static double totalDistance = 0;
    private static GPSData prevCoord;

    private static LinkedList<GPSData> coords = new LinkedList<>();

    public GPSData(double Latitude, double longitude) {
        this.Latitude = Latitude;
        this.Longitude = longitude;
        this.timeStamp = System.currentTimeMillis();


        //coords.add(this);
        if (prevCoord != null) {
            coords.add(this);
            if (System.currentTimeMillis() > coords.get(0).timeStamp + 86400000) {
                //find the distance between 0 and 1, remove from totalDistance
                double distanceToRemove = distance(coords.get(0), coords.get(1));
                totalDistance -= distanceToRemove;
                coords.remove(0);
            }
            totalDistance += distance(prevCoord, this);
        }
        prevCoord = this;
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

    public static double getTotalDistance() {
        return totalDistance;
    }

    static void increment() {
        totalDistance++;
    }

    /*public static double getTotalDistance() {
        double currentTotal = 0;
        for (int i = 0; i < coords.size() - 1; i++) {
            currentTotal += distance(coords.get(i), coords.get(i + 1));
        }
        return currentTotal;
    }*/

    /*public static double getTotalDistance(double currentTotal) {
        int size = coords.size();
        return currentTotal + distance(coords.get(size - 2), coords.get(size - 1));
    }*/


    @Override
    public String toString() {
        return "Latitude: " + Latitude + " " + "Longitude: " + Longitude;
    }
}
