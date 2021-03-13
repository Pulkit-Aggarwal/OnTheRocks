package com.example.ontherocks;

import org.junit.Test;

import static com.example.ontherocks.GPSData.distance;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void distance_is_correct() {
        GPSData a = new GPSData(10.5, 20.5);
        GPSData b = new GPSData(5.5, 2.5);
        assertEquals(distance(a, b), 2056.0, 2.0);
    }
}