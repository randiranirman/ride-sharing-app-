package com.rideshare.matching_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity

@Table(name = "rides")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Ride {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private String id ;

    @Column( nullable = false)
    private String riderId ;

    @Column( nullable = false)
    private String driverId ;
    @Column( nullable = false)

    private double pickUpLatitude;
    @Column( nullable = false)

    private  double pickupLongitude ;
    @Column( nullable = false)

    private String pickupAddress ;

    @Column( nullable = false)

    private double dropLatitude;
    @Column( nullable = false)

    private double dropLongitude ;
    @Column( nullable = false)

    private String dropAddress ;


    @Enumerated(EnumType.STRING)
    @Column( nullable = false)
    // ride status enum - tracks the life cycle
    private RideStatus status;

    // fare details
    private double estimatedFare;
    private double actualFare;


    @CreationTimestamp
    private LocalDateTime createdAt ;

    @UpdateTimestamp
    private  LocalDateTime updatedAt ;


    private LocalDateTime startedAt ;
    private LocalDateTime completedAt ;


}
