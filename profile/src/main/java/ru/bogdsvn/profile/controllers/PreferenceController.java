package ru.bogdsvn.profile.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.bogdsvn.profile.dtos.PreferenceDto;
import ru.bogdsvn.profile.services.PreferenceService;

@RequiredArgsConstructor
@RestController
public class PreferenceController {
    private final static String FILL_PREFERENCE = "/api/v1/profiles/preferences";

    private final PreferenceService preferenceService;

    @PostMapping(FILL_PREFERENCE)
    public PreferenceDto fillPreference(@RequestBody PreferenceDto preference,
                                        @RequestHeader("loggedId") String id) {
        return preferenceService.fillPreference(preference, Long.valueOf(id));
    }
}
