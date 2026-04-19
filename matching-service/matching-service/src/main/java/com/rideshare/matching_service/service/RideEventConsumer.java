package com.rideshare.matching_service.service;


import com.rideshare.matching_service.event.RideRequestedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RideEventConsumer {
    private final MatchingService matchingService;

     /*
      should consume ride requested every time it triggered
      */





    @KafkaListener(topics = "ride.requested" , groupId = "matching-service-group")
    public void consumeRideRequestedEvent(RideRequestedEvent event) {




        try{
            matchingService.matchDriverForRide(event);


        }catch (Exception ex){


            log.error("error processing ride request");
            // in production  send to dead letter queue for retry



        }
    }










}
