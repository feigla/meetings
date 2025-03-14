package ru.bogdsvn.profile.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bogdsvn.profile.dtos.ProcessedDto;
import ru.bogdsvn.profile.dtos.ProfileStatusDto;
import ru.bogdsvn.profile.saga.ProfileSaga;
import ru.bogdsvn.profile.services.DeactivatedProfileService;

@RequiredArgsConstructor
@RestController
public class DeactivatedProfileController {
    private final static String DEACTIVATE_PROFILE = "/api/v1/profiles/deactivate";
    private final static String GET_STATUS_PROFILE = "/api/v1/profiles/status";

    private final DeactivatedProfileService profileService;

    private final ProfileSaga profileSaga;

    @GetMapping(GET_STATUS_PROFILE)
    public ProfileStatusDto getStatus(@RequestHeader("loggedId") Long id) {
        return profileService.getStatus(id);
    }

    @PostMapping(DEACTIVATE_PROFILE)
    public ResponseEntity<ProcessedDto> deactivateProfile(@RequestHeader("loggedId") Long id) {
        profileService.setProfileDeactivated(id);
        profileSaga.startSaga(id);
        return ResponseEntity.ok(ProcessedDto.builder()
                .message("Запрос в обработке")
                .build());
    }
}
