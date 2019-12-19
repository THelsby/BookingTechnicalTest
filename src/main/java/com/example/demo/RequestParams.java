package com.example.demo;

import java.net.MalformedURLException;
import java.net.URL;

public class RequestParams {

    public LongLat pickup;
    public String urlExtension;
    public LongLat dropoff;
    public int passengers;
    public String MAINURL = "https://techtest.rideways.com/";

    public RequestParams(LongLat pickup, LongLat dropoff, int passengers, String urlExtension){
        this.pickup = pickup;
        this.dropoff = dropoff;
        this.passengers = passengers;
        this.urlExtension = urlExtension;
    }

    public URL buildUrl(){
        try {
            System.out.println(String.format("%s%s?pickup=%s,%s&dropoff=%s,%s", MAINURL, urlExtension, pickup.latitude, pickup.longitude, dropoff.latitude, dropoff.longitude));
            return new URL(String.format("%s%s?pickup=%s,%s&dropoff=%s,%s", MAINURL, urlExtension, pickup.latitude, pickup.longitude, dropoff.latitude, dropoff.longitude));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
