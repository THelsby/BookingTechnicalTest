package com.example.demo;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

public class Vehicle implements Comparable<Vehicle>{

    @SerializedName("car_type")
    String carType;
    float price;
    int maxPassengers;

    public Vehicle(String carType, int price) {
        this.carType = carType;
        this.price = price;
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

    @Override
    public int compareTo(@NotNull Vehicle o) {
        return Float.compare(price, o.price);
    }
}