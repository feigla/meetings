package ru.bogdsvn.location.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bogdsvn.location.dtos.LocationDto;
import ru.bogdsvn.location.services.LocationService;

@RequiredArgsConstructor
@RestController
public class LocationController {
    private final static String CREATE_LOCATION = "/api/v1/locations";
    private final static String UPDATE_LOCATION = "/api/v1/locations/{id}";

    private final LocationService locationService;

    @PostMapping(CREATE_LOCATION)
    public ResponseEntity<LocationDto> saveLocation(@RequestBody @Valid LocationDto location,
                                             @RequestHeader("loggedId") Long id) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(locationService.saveLocation(location, id));
    }

    @PutMapping(UPDATE_LOCATION)
    public ResponseEntity<Void> updateLocation(@PathVariable("id") Long id,
                                               @RequestBody @Valid LocationDto location,
                                               @RequestHeader("loggedId") Long loggedId) {
        locationService.updateLocation(location, id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
