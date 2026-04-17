package com.rideshare.location_service.dto;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public record NearByDriverResponse(


        String driverId ,
        String longitude ,

        String latitude
        ,


        double distanceInKm



) {
}
