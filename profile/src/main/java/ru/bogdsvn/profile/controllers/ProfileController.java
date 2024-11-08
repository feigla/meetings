package ru.bogdsvn.profile.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.bogdsvn.profile.services.ProfileService;

@Log4j2
@RequiredArgsConstructor
@RestController
public class ProfileController {
    private static final String CREATE_PROFILE = "/api/v1/profiles";

    private final ProfileService profileService;

    @PostMapping(CREATE_PROFILE)
    public ResponseEntity<Void> createProfile(@RequestHeader("loggedId") String id) {
        profileService.saveProfile(id);
        return ResponseEntity.ok().build();
    }
}
