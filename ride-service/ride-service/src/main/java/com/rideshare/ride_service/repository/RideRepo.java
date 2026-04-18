package com.rideshare.ride_service.repository;


import com.rideshare.ride_service.model.Rider;
import org.hibernate.tool.schema.spi.SchemaTruncator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideRepo extends JpaRepository<Rider, String> {

    List<Rider> findByRiderIdOrderByCratedAtDesc(String riderId);



}
