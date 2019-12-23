package com.example.demo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class Response {
    String body;
    int code;

    Response(String body, int code) {
        this.body = body;
        this.code = code;
    }

    /**
     * Parses the body of the response and generates the corresponding objects.
     * @param passengers
     * @return
     */
    Supplier parseJson(int passengers){

        try {
            JSONObject jsonObject = new JSONObject(this.body);
            String supplierID = jsonObject.getString("supplier_id");
            Supplier supplier = new Supplier(supplierID);
            JSONArray jsonArray = jsonObject.getJSONArray("options");
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObjectArray = jsonArray.getJSONObject(i);
                String carType = jsonObjectArray.getString("car_type");
                int price = jsonObjectArray.getInt("price");
                Vehicle vehicle = new Vehicle(carType, supplierID, price);
                supplier.addVehicles(vehicle, passengers);
            }
            return supplier;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
