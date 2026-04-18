package com.rideshare.ride_service.service;

import com.rideshare.ride_service.dto.RideRequest;
import com.rideshare.ride_service.dto.RideResponse;
import com.rideshare.ride_service.event.RideRequestedEvent;
import com.rideshare.ride_service.model.RideStatus;
import com.rideshare.ride_service.model.Rider;
import com.rideshare.ride_service.repository.RideRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RideService {

    private final RideRepo rideRepo;
    private final KafkaTemplate<String , RideRequestedEvent> kafkaTemplate;

    private static final String RIDE_REQUESTED_TOPIC ="ride.requested";
    public RideResponse requestRide(RideRequest rideRequest) {

        log.info("new ride request from rider " + rideRequest.riderId());

        Rider ride = new Rider();
        ride.setRiderId(rideRequest.riderId());
        ride.setPickUpLatitude(rideRequest.pickUpLatitude());
        ride.setPickUpLongitude(rideRequest.pickupLongitude());
        ride.setPickUpAddress(rideRequest.pickupAddress());
        ride.setDropLatitude(rideRequest.dropLatitude());
        ride.setDropLongitude(rideRequest.dropLongitude());
        ride.setDropAddress(rideRequest.dropAddress());
        ride.setStatus(RideStatus.REQUESTED);
        ride.setEstimatedFare(calculateEstimateFare( rideRequest
                ));


        //  saving  details to the DB
        Rider savedRide = rideRepo.save(ride);


        // step 2  publish even to kafka matching service will consume find nearest driver

        RideRequestedEvent event = new RideRequestedEvent(

                savedRide.getId(),
                savedRide.getRiderId(),
                savedRide.getPickUpLatitude(),
                savedRide.getPickUpLongitude(),
                savedRide.getPickUpAddress(),
                savedRide.getDropLatitude(),
                savedRide.getDropLongitude(),
                savedRide.getDropAddress()


        );

        kafkaTemplate.send(
                RIDE_REQUESTED_TOPIC ,savedRide.getId() ,event
        );
        log.info("ride requested event publised to  kafka for  ride " + savedRide.getId());


        // update status


        savedRide.setStatus(RideStatus.MATCHING);



        rideRepo.save(savedRide);

        return mapToResponse(savedRide);
        

















    }

    private double calculateEstimateFare(RideRequest rideRequest) {
    }

    private RideResponse mapToResponse(Rider savedRide) {
        return null;
    }

    public RideResponse getRideById(String rideId) {
    }

    public List<RideResponse> getRidesByRider(String riderId) {
    }

    public RideResponse startRide(String rideId) {
    }

    public RideResponse completeRide(String rideId) {
    }

    public RideResponse cancelRide(String rideId) {
    }
}
