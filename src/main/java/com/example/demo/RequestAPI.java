package com.example.demo;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class RequestAPI {

    private LongLat pickup;
    private String urlExtension;
    private LongLat dropoff;
    private int passengers;
    private final String MAINURL = "https://techtest.rideways.com/";
    private final String[] URLS = {"dave", "eric", "jeff"};
    private URL url;

    public RequestAPI(LongLat pickup, LongLat dropoff, int passengers, String urlExtension){
        this.pickup = pickup;
        this.dropoff = dropoff;
        this.passengers = passengers;
        this.urlExtension = urlExtension;
        this.url = buildUrl();
    }

    public Supplier initRequest(){
        Response response = httpRequest();
            if(response.code == 200){
                return response.parseJson(passengers);
            }else{
                return null;
            }
    }


    public URL buildUrl(){
        try {
            return new URL(String.format("%s%s?pickup=%s,%s&dropoff=%s,%s", MAINURL, urlExtension, pickup.latitude, pickup.longitude, dropoff.latitude, dropoff.longitude));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response httpRequest(){
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
