package com.example.demo;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

public class Vehicle implements Comparable<Vehicle>{

    @SerializedName("car_type")
    String carType;
    String supplierId;
    float price;
    int maxPassengers;

    /**Constructor to create Vehicles which checks the vehicle type and sets the max passengers.
     * @param carType
     * @param supplierId
     * @param price
     */
    Vehicle(String carType, String supplierId, int price) {
        this.carType = carType;
        this.price = price;
        this.supplierId = supplierId;
        switch (carType){
            case "STANDARD":
                this.maxPassengers = 4;
                break;
            case "EXECUTIVE":
                this.maxPassengers = 4;
                break;
            case "LUXURY":
                this.maxPassengers = 4;
                break;
            case "PEOPLE_CARRIER":
                this.maxPassengers = 6;
                break;
            case "LUXURY_PEOPLE_CARRIER":
                this.maxPassengers = 6;
                break;
            case "MINIBUS":
                this.maxPassengers = 16;
                break;
        }
    }

    /** Used in the sort method to compare the vehicles by price
     * @param o
     * @return
     */
    @Override
    public int compareTo(@NotNull Vehicle o) {
        return Float.compare(price, o.price);
    }
}
