package ru.bogdsvn.profile.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bogdsvn.profile.dtos.PreferenceDto;
import ru.bogdsvn.profile.services.PreferenceService;

@RequiredArgsConstructor
@RestController
public class PreferenceController {
    private final static String CREATE_PREFERENCE = "/api/v1/preferences";
    private final static String UPDATE_PREFERENCE = "/api/v1/preferences/{id}";
    private final static String GET_PREFERENCE = "/api/v1/preferences/{id}";

    private final PreferenceService preferenceService;

    @PostMapping(CREATE_PREFERENCE)
    public ResponseEntity<PreferenceDto> fillPreference(@RequestBody @Valid PreferenceDto preference,
                                                        @RequestHeader("loggedId") Long id) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(preferenceService.fillPreference(preference, id));
    }

    @PutMapping(UPDATE_PREFERENCE)
    public ResponseEntity<Void> updatePreference(@PathVariable("id") Long id,
                                                 @RequestBody @Valid PreferenceDto preference,
                                                 @RequestHeader("loggedId") Long loggedId) {
        preferenceService.updatePreference(preference, id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping(GET_PREFERENCE)
    public ResponseEntity<PreferenceDto> getPreference(@PathVariable("id") Long id,
                                       @RequestHeader("loggedId") Long loggedId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(preferenceService.getPreference(id));
    }
}
