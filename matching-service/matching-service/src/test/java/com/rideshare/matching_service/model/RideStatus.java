package com.rideshare.matching_service.model;

// requestd matched accepted    driver arrived ride started completed  cancelled
public enum RideStatus {


    REQUESTED,
    MATCHED,
    ACCEPTED,
    DRIVER_ARRIVED,
    RIDE_STARTED,
    COMPLETED
,
    CANCELLED
}
