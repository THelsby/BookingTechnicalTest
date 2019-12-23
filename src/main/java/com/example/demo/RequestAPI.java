package com.example.demo;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

class RequestAPI {

    private LatLong pickup;
    private String urlExtension;
    private LatLong dropoff;

    RequestAPI(LatLong pickup, LatLong dropoff, String urlExtension) {
        this.pickup = pickup;
        this.dropoff = dropoff;
        this.urlExtension = urlExtension;
    }


    /**
     * Generates the http request using the OkHttpClient package.
     * @return Response
     */
    Response httpRequest() {
        OkHttpClient httpClient = new OkHttpClient.Builder().connectTimeout(2, TimeUnit.SECONDS).build();
        try {
            String MAINURL = "https://techtest.rideways.com/";
            URL url = new URL(String.format("%s%s?pickup=%s,%s&dropoff=%s,%s", MAINURL, urlExtension, pickup.latitude, pickup.longitude, dropoff.latitude, dropoff.longitude));
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            try (okhttp3.Response response = httpClient.newCall(request).execute()) {
                return new Response(response.body().string(), response.code());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
