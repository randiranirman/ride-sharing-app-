package com.rideshare.location_service.service;


import com.rideshare.location_service.dto.DriverLocationRequest;
import com.rideshare.location_service.dto.NearByDriverResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
@Slf4j

@RequiredArgsConstructor
public class LocationService {

    private final RedisTemplate<String , String > redisTemplate;

    private static final String DRIVER_GEO_KEY="driver:locations";

    public  List<NearByDriverResponse> findNearByDrivers(double latitude, double longitude, double radius) {

        // find near by drivers




    }


    /** update driver location in redis
     * called every 3 seconds  by driveres phone
     *
     * @param request
     */
    public void updateDriverLocation(DriverLocationRequest request) {



        log.info("updating location for driver "  + request.driverId() );

        Point  driverPoint = new Point(


                request.longitude(),



                request.latitude()    );



        // redis geo  commands
        redisTemplate.opsForGeo()
                .add(DRIVER_GEO_KEY,driverPoint, request.driverId());

        log.info("location updated for driver " + request.driverId());






    }

    public void removeDriver(String driverId) {



    }
}
