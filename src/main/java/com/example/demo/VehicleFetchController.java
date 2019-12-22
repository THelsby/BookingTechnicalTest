package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class VehicleFetchController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String serviceCallRest(@RequestParam(name = "pickupLat") float pickupLat, @RequestParam(name = "pickupLong") float pickupLong,
                                  @RequestParam(name = "dropoffLat") float dropoffLat, @RequestParam(name = "dropoffLong") float dropoffLong,
                                  @RequestParam(name = "passengers") int passengers){
        RequestFactory requestFactory = new RequestFactory();
        System.out.println("pickupLat = " + pickupLat + "pickupLong = " + pickupLong + "dropoffLat = " + dropoffLat + "dropofflong = " + dropoffLong + "passengers = " + passengers );
        ArrayList<Supplier> suppliers = requestFactory.initRequest(new LatLong(pickupLat,pickupLong), new LatLong(dropoffLat,dropoffLong), passengers);
        Output output = new Output(suppliers);
        return output.cheapestVehicleToJson().toString();
    }
}
