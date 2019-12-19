package com.example.demo;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

class APICall {


    Response initCall(RequestParams requestParams) {
        HashMap<String, Integer> response = httpRequest(requestParams.buildUrl());
        Map.Entry<String, Integer> responseEntry = response.entrySet().iterator().next();
        if (response.entrySet().iterator().next().getValue() == 200){
            return parseJson(responseEntry.getKey(), requestParams.passengers);
        }else{
            return null;
        }
    }

    private HashMap<String,Integer> httpRequest(URL url){
         OkHttpClient httpClient = new OkHttpClient();
            Request request = new Request.Builder()
                .url(url)
                .build();

        try (okhttp3.Response response = httpClient.newCall(request).execute()) {
            String responseBody = response.body().string();
            System.out.println(responseBody);
            HashMap<String, Integer> responseHashMap = new HashMap<>();
            responseHashMap.put(responseBody, response.code());
            return responseHashMap;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private Response parseJson(String response, int passengers){

        try {
            JSONObject jsonObject = new JSONObject(response);
            String supplierID = jsonObject.getString("supplier_id");
            Response supplierObject = new Response(supplierID);
            JSONArray jsonArray = jsonObject.getJSONArray("options");
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObjectArray = jsonArray.getJSONObject(i);
                String carType = jsonObjectArray.getString("car_type");
                int price = jsonObjectArray.getInt("price");
                Vehicle vehicle = new Vehicle(carType,price);
                supplierObject.addVehicles(vehicle, passengers);
            }
            return supplierObject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
