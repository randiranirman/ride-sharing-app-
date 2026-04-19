package com.rideshare.matching_service.service;


import com.rideshare.matching_service.client.LocationServiceClient;
import com.rideshare.matching_service.dto.NearByDriverResponse;
import com.rideshare.matching_service.event.RideMatched;
import com.rideshare.matching_service.event.RideRequestedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class MatchingService {
    //ask location service to get nearby drivers
    //score drivers based on ratings distance
    // pick the best scored driver
    // publish ride matched event to kafka

    private final LocationServiceClient  locationServiceClient;
    private final KafkaTemplate<String , RideMatched> kafkaTemplate ;

    private static  final String RIDE_MATCHED_TOPIC= "ride.matched";
    private static  final double DEFAULT_RADIUS_KM= 5.0 ;


    // this is the   matching algorithm   called when ride  requested event consumed  by  user

    /*
     step 1 - ask location service to get the nearby drivers

     */
    public void matchDriverForRide(RideRequestedEvent event) {


        List<NearByDriverResponse>
                 nearByDriverResponses =  locationServiceClient.getNearByDrivers(event.getPickUpLatitude() ,
                event.getPickUpLongitude() , DEFAULT_RADIUS_KM);


        if( nearByDriverResponses.isEmpty()) {
            log.warn(" no drivers  found near ride ");

            return;
        }


        // step 2 score each driver and pick the best one


        Optional<NearByDriverResponse> bestDriver = findBestDriver ( nearByDriverResponses);

        if( bestDriver.isEmpty()) {
            log.warn(" no drivers  found near ride ");

            return;

        }


        NearByDriverResponse assignedDriver = bestDriver.get();


        // step 3 published ride matched event to kafka

        RideMatched rideMatched=  new RideMatched(

                event.getRideId(),
                event.getRiderId(),
                assignedDriver.driverId(),
                 assignedDriver.latitude(),
                assignedDriver.longitude(),
                assignedDriver.distanceInKm()

        );

        kafkaTemplate.send(RIDE_MATCHED_TOPIC, event.getRideId() , rideMatched);

        log.info("ride matched event published ");



    }


    /*
    driver scoring algorithm
     distance  70%, rating 30%
     score 1/distanceInKm * distanceWeight + rating* ratingweight
     */
    private Optional<NearByDriverResponse> findBestDriver(List<NearByDriverResponse> drivers) {

        double distanceWeight = 0.7;
        double ratingWeight = 0.3 ;


        return drivers.stream().max(Comparator.comparingDouble(
                driver -> {
                    // distance score  close = higherscore
                    // distance 0 .1 avoid devision by zero
                }
        ))





    }





}
