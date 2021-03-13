package com.example.ontherocks;

import java.util.ArrayList;

public class GPSData {
    double Latitude;
    double Longitude;


    public GPSData(double Latitude, double longitude) {
        this.Latitude = Latitude;
        this.Longitude = longitude;
    }

    @Override
    public String toString() {
        return "Latitude: " + Latitude + " " + "Longitude" + Longitude;
    }
}
