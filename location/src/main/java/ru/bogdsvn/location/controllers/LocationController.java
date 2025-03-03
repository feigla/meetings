package ru.bogdsvn.location.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.bogdsvn.location.dtos.LocationDto;
import ru.bogdsvn.location.services.LocationService;

@RequiredArgsConstructor
@RestController
public class LocationController {
    private final static String SAVE_LOCATION = "/api/v1/locations";

    private final LocationService locationService;

    @PostMapping(SAVE_LOCATION)
    public ResponseEntity<Void> saveLocation(@RequestBody LocationDto location,
                                             @RequestHeader("loggedId") String id) {
        locationService.saveLocation(location, Long.parseLong(id));
        return ResponseEntity.ok().build();
    }
}
