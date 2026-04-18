package com.rideshare.ride_service.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequestedEvent {

    private String rideId;
    private String riderId;
    double pickUpLatitude;


    double pickUpLongitude;

    String pickUpAddress;

    double dropLatitude;

    double dropLongitude;

    String dropAddress;
}


