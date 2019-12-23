package com.example.demo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collections;

public class Supplier {

    @SerializedName("supplier_id")
    String supplierID;
    @SerializedName("options")
    ArrayList<Vehicle> vehicles = new ArrayList<>();


    Supplier(String supplierID) {
        this.supplierID = supplierID;
    }

    /**
     * Checks if the passenger requirement meets the demand and adds it to the Array if so.
     * @param vehicle
     * @param passengers
     */
    void addVehicles(Vehicle vehicle, int passengers){
        if(vehicle.maxPassengers >= passengers) {
            vehicles.add(vehicle);
        }
    }

    /**
     * Sorts the ArrayList of vehicles by price.
     */
    void sort() {
        Collections.sort(this.vehicles);
    }


}
