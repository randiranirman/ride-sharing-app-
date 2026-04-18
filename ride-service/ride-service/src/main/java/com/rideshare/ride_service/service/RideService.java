package com.rideshare.ride_service.service;

import com.rideshare.ride_service.dto.RideRequest;
import com.rideshare.ride_service.dto.RideResponse;
import com.rideshare.ride_service.event.RideRequestedEvent;
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
