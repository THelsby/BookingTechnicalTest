package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sun.misc.Request;

import java.util.ArrayList;

@SpringBootApplication
public class Driver {

    public static void main(String[] args){

        SpringApplication.run(Driver.class, args);
            final String[] URLS = {"dave", "eric", "jeff"};

        ArrayList<Supplier> suppliers = new ArrayList<>();
        for(String url: URLS){
            RequestAPI requestAPI = new RequestAPI(new LongLat(Float.valueOf(args[0]),
                    Float.valueOf(args[1])), new LongLat(Float.valueOf(args[2]), Float.valueOf(args[3])),
                    Integer.parseInt(args[4]), url);
            suppliers.add(requestAPI.initRequest());
        }


        ArrayList<Vehicle> allVehicles = new ArrayList<>();
        for(Supplier supplier: suppliers){
            allVehicles.addAll(supplier.getAllVehicles());
            supplier.sort();
            supplier.printToConsole();
        }
        System.out.println("ALL VEHICLES");
        for (Vehicle vehicle: allVehicles){
            System.out.println(vehicle.carType + " - " + vehicle.price);
        }

    }

}
