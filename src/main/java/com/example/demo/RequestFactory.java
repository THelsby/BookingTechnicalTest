package com.example.demo;

import java.util.ArrayList;

class RequestFactory {

    /**Takes in the parameters for the API and returns an ArrayList of Suppliers and their corresponding vehicles.
     * @param latitude
     * @param longitude
     * @param passengers
     * @return
     */
    ArrayList<Supplier> initRequest(LatLong latitude, LatLong longitude, int passengers){
        final String[] URLS = {"dave", "eric", "jeff"};
        return setupRequest(latitude, longitude, passengers, URLS);
    }

    /**Takes in the parameters for the API and returns an ArrayList of Suppliers and their corresponding vehicles.
     * @param latitude
     * @param longitude
     * @return
     */
    ArrayList<Supplier> initRequest(LatLong latitude, LatLong longitude) {
        final String[] URLS = {"dave"};
        return setupRequest(latitude, longitude, 0, URLS);
    }

    /**Skeleton for requesting the techtest API.
     * @param latitude
     * @param longitude
     * @param passengers
     * @param urls
     * @return
     */
    private ArrayList<Supplier> setupRequest(LatLong latitude, LatLong longitude, int passengers, String[] urls){
        ArrayList<Supplier> suppliers = new ArrayList<>();
        for (String url : urls) {
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
