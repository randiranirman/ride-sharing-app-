package com.rideshare.ride_service.repository;

import com.rideshare.ride_service.model.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepo extends JpaRepository<String , Long> {



}
