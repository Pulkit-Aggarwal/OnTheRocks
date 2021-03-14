package com.example.ontherocks;

public class CO2data {

    final static double kmtomiles = 3956.0/6371.0;
    final static double car = 0.3;
    final static double train = 0.09;
    final static double bus = 0.15;
    //Rate per tonne of c02
    final static double rate = 80;

    public static double emissionRate(String type, double dist) {
        switch (type) {
            case "car":
                return carEmission(dist);
            case "bus":
                return busEmission(dist);
            case "train":
                return trainEmission(dist);
            default:
                return 0.00; // Error handler or peoples
        }
    }

    // in kg
    public static double carEmission (double dist) {
        return dist*kmtomiles*car;
    }

    // in kg
    public static double busEmission (double dist) {
        return dist*kmtomiles*bus;
    }

    // in kg
    public static double trainEmission (double dist) {
        return dist*kmtomiles*train;
    }


    public static double cost (double kgemission) {
        return kgemission*rate/1000;
    }


}
