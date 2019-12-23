package com.example.demo;

import com.sun.javafx.binding.StringFormatter;

import java.util.ArrayList;

/**
 * Skeleton for requesting the techtest API.
 */
class RequestFactory {


    /**
     * Takes in the parameters for the API and returns an ArrayList of Suppliers and their corresponding vehicles.
     * @param latitude
     * @param longitude
     * @param passengers
     * @return
     */
    ArrayList<Supplier> initRequest(LatLong latitude, LatLong longitude, int passengers){
        final String[] URLS = {"dave", "eric", "jeff"};

        ArrayList<Supplier> suppliers = new ArrayList<>();
        for (String url : URLS) {
            RequestAPI requestAPI = new RequestAPI(latitude, longitude, url);
            Response response = requestAPI.httpRequest();
            System.out.println(response.body);
            if (response.code == 200) {
                suppliers.add(response.parseJson(passengers));
            } else {
                System.out.println(String.format("Failed to retrieve results from = %s - Response code = %d", url, response.code));
            }
        }
        return suppliers;
    }
}