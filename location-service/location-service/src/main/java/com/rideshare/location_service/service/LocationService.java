package com.rideshare.location_service.service;


import com.rideshare.location_service.dto.DriverLocationRequest;
import com.rideshare.location_service.dto.NearByDriverResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.geo.*;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j

@RequiredArgsConstructor
public class LocationService {

    private final RedisTemplate<String , String > redisTemplate;

    private static final String DRIVER_GEO_KEY="driver:locations";

    public  List<NearByDriverResponse> findNearByDrivers(double latitude, double longitude, double radius) {

        // find nearby drivers
      // called by matching service on ride request
         //  maps to redis  GEORADIUS command


        log.info("finding drivers near lat : {} long: {} within radius : {} " , latitude, longitude, radius);


        // created the search area
        Circle searchArea=  new Circle( new Point(longitude, latitude) , new Distance(

                radius, Metrics.KILOMETERS
        ));


         GeoResults<RedisGeoCommands.GeoLocation<String>>   results = redisTemplate
                 .opsForGeo().radius(
                         DRIVER_GEO_KEY ,
                         searchArea,
                         RedisGeoCommands.GeoRadiusCommandArgs
                                 .newGeoRadiusArgs()
                                 .includeDistance()
                                 .sortAscending()
                                 .limit(10)
                 );

         List<NearByDriverResponse> nearByDrivers = new ArrayList<>();

         if( results != null) {

             results.getContent().forEach(

                     result -> {
                         RedisGeoCommands.GeoLocation<String> location= result.getContent();

                         nearByDrivers.add( new NearByDriverResponse(

                                 location.getName(),
                                 location.getPoint().getX(),
                                 location.getPoint().getY(),
                                 result.getDistance().getValue()
                         ));
                     }
             );
         }


         return nearByDrivers;








    }


    /** update driver location in redis
     * called every 3 seconds  by driveres phone
     *
     *
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

        log.info("removing driver ");
        redisTemplate.opsForGeo().remove(DRIVER_GEO_KEY ,  driverId
        );






    }
}
