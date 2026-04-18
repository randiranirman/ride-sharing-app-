package com.rideshare.ride_service.controller;


import com.rideshare.ride_service.dto.RideRequest;
import com.rideshare.ride_service.dto.RideResponse;
import com.rideshare.ride_service.service.RideService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/v1/rides")
@Slf4j
@RequiredArgsConstructor

public class RideController {

    private final RideService rideService;


    // rider request for a  new ride



    @PostMapping("/request")
    public RideResponse  requestRide(@Valid @RequestBody RideRequest  rideRequest) {


        log.info("ride requset received from rider " + rideRequest.riderId());


    RideResponse response = rideService.requestRide( rideRequest);

        return  response;


    }

    @GetMapping("/{rideId}")
    public RideResponse getRideById(@PathVariable  String rideId){


        return rideService.getRideById( rideId);

    }


    @GetMapping("/rider/{riderId}")
    public List<RideResponse> getRidesByRider( @PathVariable String riderId) {

        return rideService.getRidesByRider( riderId);

    }

    // driver starts the ride


    @PutMapping("/{rideId}/start")
     public RideResponse startRide( @PathVariable String rideId) {

        return rideService.startRide( rideId);

     }



     @PutMapping("/{rideId}/complete")

     public RideResponse completeRide( @PathVariable String  rideId){


        return  rideService.completeRide( rideId);

     }


     @PutMapping("/{rideId}/cancel")
      public RideResponse cancelRide( @PathVariable String rideId){

        return rideService.cancelRide( rideId);

      }

}
