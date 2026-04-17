package com.rideshare.location_service.controller;


import com.rideshare.location_service.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/api/v1/locations")
@Slf4j

@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService ;



}
