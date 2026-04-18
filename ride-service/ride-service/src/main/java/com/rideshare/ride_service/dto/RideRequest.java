package com.rideshare.ride_service.dto;

import jakarta.validation.constraints.*;

public record RideRequest(

        @NotBlank(message = "Rider id is required")
        String riderId,

        @NotNull(message = "Pickup latitude is required")
        @DecimalMin(value = "-90.0", message = "Pickup latitude must be >= -90")
        @DecimalMax(value = "90.0", message = "Pickup latitude must be <= 90")
        Double pickUpLatitude,

        @NotNull(message = "Pickup longitude is required")
        @DecimalMin(value = "-180.0", message = "Pickup longitude must be >= -180")
        @DecimalMax(value = "180.0", message = "Pickup longitude must be <= 180")
        Double pickupLongitude,

        @NotBlank(message = "Pickup address is required")
        String pickupAddress,

        @NotNull(message = "Drop latitude is required")
        @DecimalMin(value = "-90.0", message = "Drop latitude must be >= -90")
        @DecimalMax(value = "90.0", message = "Drop latitude must be <= 90")
        Double dropLatitude,

        @NotNull(message = "Drop longitude is required")
        @DecimalMin(value = "-180.0", message = "Drop longitude must be >= -180")
        @DecimalMax(value = "Drop longitude must be <= 180")
        Double dropLongitude,

        @NotBlank(message = "Drop address is required")
        String dropAddress
) {}