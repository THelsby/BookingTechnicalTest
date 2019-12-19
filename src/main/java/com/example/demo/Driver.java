package com.example.demo;

import org.json.JSONException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Supplier;

@SpringBootApplication
public class Driver {

    public static void main(String[] args){

        SpringApplication.run(Driver.class, args);
            final String[] URLS = {"dave", "eric", "jeff"};


        ArrayList<RequestParams> requestParams = new ArrayList<>();
        for(String url: URLS){
            requestParams.add(new RequestParams(new LongLat(Float.valueOf(args[0]),
                    Float.valueOf(args[1])), new LongLat(Float.valueOf(args[2]), Float.valueOf(args[3])),
                    Integer.parseInt(args[4]), url));
        }

        APICall apiCall = new APICall();
        ArrayList<Response> responses = new ArrayList<>();
        for(RequestParams requestParam: requestParams){
            Response response = apiCall.initCall(requestParam);
            if(response!=null) {
                responses.add(response);
            }
        }

        ArrayList<Vehicle> allVehicles = new ArrayList<>();
        for(Response response: responses){
            allVehicles.addAll(response.getAllVehicles());
            response.printToConsole();
        }
        System.out.println("ALL VEHICLES");
        for (Vehicle vehicle: allVehicles){
            System.out.println(vehicle.carType + " - " + vehicle.price);
        }

    }

}
