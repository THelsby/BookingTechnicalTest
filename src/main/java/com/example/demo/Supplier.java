package com.example.demo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collections;

public class Supplier {

    @SerializedName("supplier_id")
    String supplierID;
    @SerializedName("options")
    ArrayList<Vehicle> vehicles = new ArrayList<>();


    public Supplier(String supplierID) {
        this.supplierID = supplierID;
    }

    public void addVehicles(Vehicle vehicle, int passengers){
        if(vehicle.maxPassengers >= passengers) {
            vehicles.add(vehicle);
        }
    }

    public Supplier sort() {
        Collections.sort(this.vehicles);
        return this;
    }

    public ArrayList<Vehicle> getAllVehicles(){
        return new ArrayList<>(this.vehicles);
    }

    public void printToConsole(){
        System.out.println(this.supplierID);
        for(Vehicle vehicle: this.vehicles){
            System.out.println(String.format("%s - %s - %.2f", vehicle.carType, this.supplierID, vehicle.price));
        }
    }

}
