package ru.bogdsvn.profile.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bogdsvn.profile.dtos.BioDto;
import ru.bogdsvn.profile.services.BioService;

@RequiredArgsConstructor
@RestController
public class BioController {
    private final static String CREATE_BIO = "/api/v1/bios";
    private final static String UPDATE_BIO = "/api/v1/bios/{id}";
    private final static String GET_BIO = "/api/v1/bios/{id}";

    private final BioService bioService;

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
}
