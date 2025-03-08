package ru.bogdsvn.profile.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.bogdsvn.profile.dtos.PreferenceDto;
import ru.bogdsvn.profile.services.PreferenceService;

@RequiredArgsConstructor
@RestController
public class PreferenceController {
    private final static String FILL_PREFERENCE = "/api/v1/profiles/preferences";

    private final PreferenceService preferenceService;

    @PostMapping(FILL_PREFERENCE)
    public PreferenceDto fillPreference(@RequestBody @Valid PreferenceDto preference,
                                        @RequestHeader("loggedId") Long id) {
        return preferenceService.fillPreference(preference, id);
    }

    @PutMapping(FILL_PREFERENCE)
    public PreferenceDto updatePreference(@RequestBody @Valid PreferenceDto preference,
                                          @RequestHeader("loggedId") Long id) {
        return preferenceService.updatePreference(preference, id);
    }
}
