package com.example.demo;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

class RequestAPI {

    private LatLong pickup;
    private String urlExtension;
    private LatLong dropoff;
    private final String MAINURL = "https://techtest.rideways.com/";
    private URL url;

    RequestAPI(LatLong pickup, LatLong dropoff, String urlExtension){
        this.pickup = pickup;
        this.dropoff = dropoff;
        this.urlExtension = urlExtension;
        this.url = buildUrl();
    }


    private URL buildUrl(){
        try {
            return new URL(String.format("%s%s?pickup=%s,%s&dropoff=%s,%s", MAINURL, urlExtension, pickup.latitude, pickup.longitude, dropoff.latitude, dropoff.longitude));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    Response httpRequest(){
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(this.url)
                .build();

        try (okhttp3.Response response = httpClient.newCall(request).execute()) {
            return new Response(response.body().string(), response.code());
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
