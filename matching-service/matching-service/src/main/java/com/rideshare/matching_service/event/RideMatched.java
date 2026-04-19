package com.rideshare.matching_service.event;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class  RideMatched {

    private String rideId ;

    private String riderId ;
    private String driverId ;
    private double driverLatitude ;
    private double driverLongitude ;
    private double distantToPickup ;







}
