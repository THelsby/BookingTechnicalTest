package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Driver {

    /**
     * Main method which checks argument length to decide whether to run commandline version or SpringApplication
     * @param args
     */
    public static void main(String[] args) {
        if(args.length == 0) {
            SpringApplication.run(Driver.class, args);
        }else{
            ArrayList<Supplier> suppliers = null;
            RequestFactory requestFactory = new RequestFactory();
            if(args.length == 4) {
                suppliers = requestFactory.initRequest(new LatLong(Float.valueOf(args[0]),
                        Float.valueOf(args[1])), new LatLong(Float.valueOf(args[2]), Float.valueOf(args[3])));
            }else{
                suppliers = requestFactory.initRequest(new LatLong(Float.valueOf(args[0]),
                        Float.valueOf(args[1])), new LatLong(Float.valueOf(args[2]), Float.valueOf(args[3])), Integer.parseInt(args[4]));
            }
            Output output = new Output(suppliers);
            output.printCheapestVehicles();
        }
    }

}
