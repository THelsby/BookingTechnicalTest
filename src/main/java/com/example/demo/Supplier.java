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

    void addVehicles(Vehicle vehicle, int passengers){
        if(vehicle.maxPassengers >= passengers) {
            vehicles.add(vehicle);
        }
    }

    void sort() {
        Collections.sort(this.vehicles);
    }

    public ArrayList<Vehicle> getAllVehicles(){
        return new ArrayList<>(this.vehicles);
    }


}
