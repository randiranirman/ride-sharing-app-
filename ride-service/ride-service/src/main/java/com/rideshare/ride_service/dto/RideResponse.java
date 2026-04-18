package com.rideshare.ride_service.dto;

import com.rideshare.ride_service.model.RideStatus;




import java.time.LocalDateTime;

public record RideResponse(


        Long id  ,






 String riderId ,


  String driverId ,


 double pickUpLatitude ,


 double pickUpLongitude  ,

 String pickUpAddress  ,

 double dropLatitude  ,

 double dropLongitude  ,

 String dropAddress ,



 RideStatus status ,


 double estimatedFare  ,

 double actualFare ,


 LocalDateTime cratedAt  ,

 LocalDateTime updateAt  ,
  LocalDateTime staredAt  ,
 LocalDateTime completedAt

) {
}
