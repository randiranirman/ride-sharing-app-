package com.rideshare.matching_service.service;


import com.rideshare.matching_service.client.LocationServiceClient;
import com.rideshare.matching_service.event.RideMatched;
import com.rideshare.matching_service.event.RideRequestedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;




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
    public void matchDriverForRide(RideRequestedEvent event) {




    }






}
