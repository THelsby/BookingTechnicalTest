package com.example.demo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Output {
    private HashMap<String, Vehicle> cheapestOptions = new HashMap<>();
    private ArrayList<Supplier> suppliers;

    Output(ArrayList<Supplier> suppliers){
        this.suppliers = suppliers;
        this.cheapestOptions.put("STANDARD", null);
        this.cheapestOptions.put("EXECUTIVE", null);
        this.cheapestOptions.put("LUXURY", null);
        this.cheapestOptions.put("PEOPLE_CARRIER", null);
        this.cheapestOptions.put("LUXURY_PEOPLE_CARRIER", null);
        this.cheapestOptions.put("MINIBUS", null);
    }

    public HashMap<String, Vehicle> calculateCheapestVehicles(){
        for (Supplier supplier : suppliers) {
            supplier.sort();
            for (Vehicle vehicle : supplier.vehicles) {
                if(cheapestOptions.get(vehicle.carType)==null){
                    cheapestOptions.put(vehicle.carType,vehicle);
                }else if(cheapestOptions.get(vehicle.carType).price > vehicle.price){
                    cheapestOptions.put(vehicle.carType,vehicle);
                }
            }
        }
        return cheapestOptions;
    }

    void printCheapestVehicles(){
        calculateCheapestVehicles();
        for (Map.Entry<String, Vehicle> vehicleEntry : cheapestOptions.entrySet()) {
            if(vehicleEntry.getValue()!=null) {
                Vehicle vehicle = vehicleEntry.getValue();
                System.out.println(String.format("%s - %s - %.2f", vehicle.carType, vehicle.supplierId, vehicle.price));
            }
        }
    }

    public JSONArray cheapestVehicleToJson(){
        calculateCheapestVehicles();
        JSONArray jsonArray = new JSONArray();
        for (Map.Entry<String, Vehicle> vehicleEntry : cheapestOptions.entrySet()) {
            if(vehicleEntry.getValue()!=null) {
                Vehicle vehicle = vehicleEntry.getValue();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("price", vehicle.price);
                    jsonObject.put("supplier_id", vehicle.supplierId);
                    jsonObject.put("car_type", vehicle.carType);
                    jsonArray.put(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonArray;
    }

}
