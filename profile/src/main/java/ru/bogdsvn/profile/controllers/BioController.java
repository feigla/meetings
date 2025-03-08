package ru.bogdsvn.profile.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.bogdsvn.profile.dtos.BioDto;
import ru.bogdsvn.profile.services.BioService;

@RequiredArgsConstructor
@RestController
public class BioController {
    private final static String FILL_BIO = "/api/v1/profiles/bios";
    private final static String GET_BIO = "/api/v1/profiles/bios";

    private final BioService bioService;

    @PostMapping(FILL_BIO)
    public BioDto fillBio(@RequestBody @Valid BioDto bio,
                          @RequestHeader("loggedId") Long id) {
        return bioService.fillBio(bio, id);
    }

    @PutMapping(FILL_BIO)
    public BioDto updateBio(@RequestBody @Valid BioDto bio,
                          @RequestHeader("loggedId") Long id) {
        return bioService.updateBio(bio, id);
    }

    @GetMapping(GET_BIO)
    public BioDto getBio(@RequestHeader("loggedId") Long id) {
        return bioService.getBio(id);
    }
}
