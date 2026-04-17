package com.rideshare.location_service.controller;


import com.rideshare.location_service.dto.DriverLocationRequest;
import com.rideshare.location_service.dto.NearByDriverResponse;
import com.rideshare.location_service.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/v1/locations")
@Slf4j

@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService ;

    // driver phone calls every 3 seconds

    @PostMapping("/drivers/update")
    public ResponseEntity<String>  updateDriverLocation(@RequestBody DriverLocationRequest request) {



        locationService.updateDriverLocation(request)
;

        return ResponseEntity.ok("Driver Location  Upadated");




    }




    // matching service calls this when ride is requested
    @GetMapping("/drivers/nearby")
    public List<NearByDriverResponse> getNearByDrivers(@RequestParam double latitude,@RequestParam
                                                       double longitude , @RequestParam( defaultValue = "5.0" ) double radius) {




        return LocationService.findNearByDrivers( latitude, longitude, radius);



    }

    // when driver goes  offline

    @DeleteMapping("/drivers/{driverId}")
    public String removeDriverByDriverId( @PathVariable String driverId ) {


        locationService.removeDriver( driverId);

        return " driver removed ";
    }



}
