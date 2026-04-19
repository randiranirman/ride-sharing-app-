package com.rideshare.matching_service.event;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// even consumed  by the rider service published by ride service when ride requested
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
