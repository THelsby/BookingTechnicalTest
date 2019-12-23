package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class VehicleFetchController {

    /**
     * Creates the end point for user to access the API and returns a JSON.
     * @param pickupLat
     * @param pickupLong
     * @param dropoffLat
     * @param dropoffLong
     * @param passengers
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String serviceCallRest(@RequestParam(name = "pickupLat") float pickupLat, @RequestParam(name = "pickupLong") float pickupLong,
                                  @RequestParam(name = "dropoffLat") float dropoffLat, @RequestParam(name = "dropoffLong") float dropoffLong,
                                  @RequestParam(name = "passengers") int passengers){
        RequestFactory requestFactory = new RequestFactory();
        ArrayList<Supplier> suppliers = requestFactory.initRequest(new LatLong(pickupLat,pickupLong), new LatLong(dropoffLat,dropoffLong), passengers);
        Output output = new Output(suppliers);
        return output.cheapestVehicleToJson();
    }
}
