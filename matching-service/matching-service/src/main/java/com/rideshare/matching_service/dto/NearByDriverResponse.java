package com.rideshare.matching_service.dto;




// this response  is from the location service nearby response
public record NearByDriverResponse(
        String driverId ,
        double  longitude ,

        double latitude
        ,


        double distanceInKm
) {





}
