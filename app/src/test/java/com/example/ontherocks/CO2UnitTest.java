package com.example.ontherocks;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CO2UnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void calculationTest() {
        GPSData dataNY = new GPSData(40.7128, 74.0060); // New York
        GPSData dataL = new GPSData(51.5074, 0.1278); // London
        double distance = dataL.distance(dataNY, dataL);
        System.out.println("Distance is " + distance);
    }
}