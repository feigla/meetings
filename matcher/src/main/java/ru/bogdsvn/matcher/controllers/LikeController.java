package ru.bogdsvn.matcher.controllers;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bogdsvn.matcher.dtos.LikeDto;
import ru.bogdsvn.matcher.services.LikeService;

@RequiredArgsConstructor
@RestController
public class LikeController {
    private final LikeService likeService;

    private static final String CREATE_LIKE = "/api/v1/likes";
    private static final String DELETE_LIKE = "/api/v1/likes/{liked_id}";
    private static final String HAS_LIKE = "/api/v1/likes/{liked_id}";

    @PostMapping(CREATE_LIKE)
    public ResponseEntity<LikeDto> setLike(@NotNull @RequestParam("liked_id") Long likedId,
                                           @RequestHeader("loggedId") Long id
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(likeService.saveLike(id, likedId));
    }

    @GetMapping(HAS_LIKE)
    public ResponseEntity<Boolean> hasLike(@NotNull @PathVariable("liked_id") Long likedId,
                                           @RequestHeader("loggedId") Long id
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(likeService.hasLike(id, likedId));
    }

    @DeleteMapping(DELETE_LIKE)
    public ResponseEntity<Void> deleteLike(@PathVariable("liked_id") Long likedId,
                                           @RequestHeader("loggedId") Long id
    ) {
        likeService.deleteLike(id, likedId);
        return ResponseEntity.noContent().build();
    }
}
