package com.rideshare.ride_service.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Data
@Entity
@Table ( name = "riders")
@AllArgsConstructor
@NoArgsConstructor

public class Rider {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id ;




    @Column( nullable = false)

    private String riderId ;

    @Column( nullable = false)
    private  String driverId ;

    @Column( nullable = false)

    private double pickUpLatitude;
    @Column( nullable = false)


    private double pickUpLongitude ;
    @Column( nullable = false)

    private String pickUpAddress ;
    @Column( nullable = false)

    private double dropLatitude ;
    @Column( nullable = false)

      private double dropLongitude ;
    @Column( nullable = false)

      private String dropAddress ;


    @Enumerated( EnumType.STRING)
    @Column( nullable = false)

      private RideStatus status;

    @Column( nullable = false)

      private double estimatedFare ;
    @Column( nullable = false)

      private double actualFare ;


    @CreationTimestamp
      private LocalDateTime cratedAt ;

    @UpdateTimestamp
      private LocalDateTime updateAt ;
      private  LocalDateTime staredAt ;
      private LocalDateTime completedAt  ;





}
