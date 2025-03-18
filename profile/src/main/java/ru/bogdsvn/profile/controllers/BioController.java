package ru.bogdsvn.profile.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bogdsvn.kafka_library.utils.Status;
import ru.bogdsvn.profile.dtos.BioDto;
import ru.bogdsvn.profile.dtos.ProcessedDto;
import ru.bogdsvn.profile.dtos.ProfileStatusDto;
import ru.bogdsvn.profile.saga.ProfileSaga;
import ru.bogdsvn.profile.services.BioService;

@RequiredArgsConstructor
@RestController
public class BioController {
    private final static String CREATE_BIO = "/api/v1/bios";
    private final static String UPDATE_BIO = "/api/v1/bios/{id}";
    private final static String GET_BIO = "/api/v1/bios/{id}";

    private final static String GET_STATUS = "/api/v1/bios/{id}/status";
    private final static String UPDATE_STATUS = "/api/v1/bios/{id}/status";

    private final BioService bioService;
    private final ProfileSaga profileSaga;

    @PostMapping(CREATE_BIO)
    public ResponseEntity<BioDto> fillBio(@RequestBody @Valid BioDto bio,
                                          @RequestHeader("loggedId") Long id) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bioService.fillBio(bio, id));
    }

    @PutMapping(UPDATE_BIO)
    public ResponseEntity<Void> updateBio(@PathVariable("id") Long id,
                            @RequestBody @Valid BioDto bio,
                            @RequestHeader("loggedId") Long loggedId) {
        bioService.updateBio(bio, id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping(GET_BIO)
    public ResponseEntity<BioDto> getBio(@PathVariable("id") Long id,
                         @RequestHeader("loggedId") Long loggedId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bioService.getBio(id));
    }

    @GetMapping(GET_STATUS)
    public ProfileStatusDto getStatus(@PathVariable("id") Long id,
                                      @RequestHeader("loggedId") Long loggedId) {
        return bioService.getStatus(id);
    }

    @PutMapping(UPDATE_STATUS)
    public ResponseEntity<ProcessedDto> updateStatus(@PathVariable("id") Long id,
                                                     @RequestParam("active") Boolean active,
                                                     @RequestHeader("loggedId") Long loggedId) {
        if (active) {
            bioService.setStatus(id, Status.ACTIVATE_PROCESSED);
            profileSaga.startSaga(id, Status.ACTIVATE_PROCESSED);
        } else {
            bioService.setStatus(id, Status.DEACTIVATE_PROCESSED);
            profileSaga.startSaga(id, Status.DEACTIVATE_PROCESSED);
        }
        return ResponseEntity.ok(ProcessedDto.builder()
                .message("Запрос в обработке")
                .build());
    }
}
