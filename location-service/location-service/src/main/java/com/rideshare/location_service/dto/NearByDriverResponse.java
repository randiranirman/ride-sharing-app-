package com.rideshare.location_service.dto;


import lombok.AllArgsConstructor;


public record NearByDriverResponse(


        String driverId ,
        double  longitude ,

        double latitude
        ,


        double distanceInKm



) {
}
