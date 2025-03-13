package ru.bogdsvn.profile.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.bogdsvn.profile.services.ProfileService;

@RequiredArgsConstructor
@RestController
public class ProfileController {
    private final static String DEACTIVATE_PROFILE = "/api/v1/profiles/deactivate";

    private final ProfileService profileService;

    @DeleteMapping(DEACTIVATE_PROFILE)
    public ResponseEntity<String> deactivateProfile(@RequestHeader("loggedId") Long id) {
        profileService.deactivateProfile(id);
        return ResponseEntity.ok("Операция в обработке");
    }
}
