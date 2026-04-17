package com.rideshare.location_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
public record DriverLocationRequest(
        String driverId ,
         double latitude ,

        double longitude



) {
}
