package com.example.demo;

import java.util.ArrayList;

class RequestFactory {


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
                System.out.println("Response code = " + response.code);
            }
        }
        return suppliers;
    }
}
