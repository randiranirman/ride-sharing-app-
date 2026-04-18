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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        log.info("ride requested event published  to  kafka for  ride " + savedRide.getId());




        // update status


        savedRide.setStatus(RideStatus.MATCHING);



        // update status

        rideRepo.save(savedRide);

        return mapToResponse(savedRide);
        

















    }

    private double calculateEstimateFare(RideRequest rideRequest) {
        // simplified haversine distance calculation


        double lat1 = Math.toRadians(rideRequest.pickUpLatitude());
        double lat2 = Math.toRadians(rideRequest.dropLatitude());
        double long1= Math.toRadians(rideRequest.pickupLongitude());
        double long2 = Math.toRadians(rideRequest.dropLongitude());


        double dlat  = lat2 - lat1;
        double dlong  = long2 - long1;


        double a = Math.pow(Math.sin(dlat /2 ) ,2) + Math.cos(lat1)* Math.cos(lat2)*Math.pow(Math.sin(dlong/2),2);

        double c =  2* Math.asin(Math.sqrt(a));


        double distance= 6371 * c ;


        // base fare 100 + 12perKm

        double  fare = 50  + (distance * 12);

        return Math.round( fare * 100.0)/100.0;

    }


    public void updateRideWithDriver( String rideId ,  String driverId) {

        Rider ride = rideRepo.findById(rideId)
                .orElseThrow(


                        () ->  new RuntimeException("Ride not  found" )

                ) ;
        ride.setDriverId(driverId);
        ride.setStatus(RideStatus.ACCEPTED);


        rideRepo.save(ride)
;


    }
    public  RideResponse mapToResponse(Rider savedRide) {
        return null;
    }

    public RideResponse getRideById(String rideId) {
        Rider ride = rideRepo.findById(rideId)
                .orElseThrow( () ->   new RuntimeException("ride not found"));

        return mapToResponse(ride);


    }

    public List<RideResponse> getRidesByRider(String riderId) {


        return rideRepo.findByRiderIdOrderByCratedAtDesc(riderId).stream().map(

                this:: mapToResponse
        ).collect(Collectors.toList());

    }

    public RideResponse startRide(String rideId) {

        Rider ride = rideRepo.findById(rideId)
                .orElseThrow( () ->   new RuntimeException("ride not found"));


        if( ride.getStatus() != RideStatus.ACCEPTED){
            throw new RuntimeException("Ride cannot  be  started");
        }




        ride.setStatus(RideStatus.RIDE_STARTED);
        ride.setStaredAt(LocalDateTime.now());

        rideRepo.save(ride);

        return mapToResponse( ride);


    }

    public RideResponse completeRide(String rideId) {
        Rider ride = rideRepo.findById(rideId)
                .orElseThrow( () ->   new RuntimeException("ride not found"));


        if( ride.getStatus() != RideStatus.RIDE_STARTED){
            throw new RuntimeException("Ride cannot  be  completed");
        }




        ride.setStatus(RideStatus.COMPLETED);
        ride.setActualFare(ride.getEstimatedFare());
        ride.setCompletedAt(LocalDateTime.now());

        rideRepo.save(ride);

        return mapToResponse( ride);



    }

    public RideResponse cancelRide(String rideId) {

        Rider ride = rideRepo.findById(rideId)
                .orElseThrow( () ->   new RuntimeException("ride not found"));







        ride.setStatus(RideStatus.CANCELLED);


        rideRepo.save(ride);

        return mapToResponse( ride);
    }
}
