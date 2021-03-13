package com.example.ontherocks;

public class CO2data {

    final double kmtomiles = 3956.0/6371.0;
    final double car = 0.3;
    final double train = 0.09;
    final double bus = 0.15;
    final double rate = 80;

    // in kg
    public double carEmission (double dist) {
        return dist*kmtomiles*car;
    }

    // in kg
    public double busEmission (double dist) {
        return dist*kmtomiles*bus;
    }

    // in kg
    public double trainEmission (double dist) {
        return dist*kmtomiles*train;
    }


    public double cost (double kgemission) {
        return kgemission*rate/10000;
    }


}
