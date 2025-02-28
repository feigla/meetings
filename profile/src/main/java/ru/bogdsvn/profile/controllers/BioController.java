package ru.bogdsvn.profile.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.bogdsvn.profile.dtos.BioDto;
import ru.bogdsvn.profile.services.BioService;

@RequiredArgsConstructor
@RestController
public class BioController {
    private final static String FILL_BIO = "/api/v1/profiles/bios";

    private final BioService bioService;

    @PostMapping(FILL_BIO)
    public BioDto fillBio(@RequestBody BioDto bio,
                          @RequestHeader("loggedId") String id) {
        return bioService.fillBio(bio, Long.valueOf(id));
    }
}
