package ru.bogdsvn.matcher.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bogdsvn.matcher.dtos.LikeDto;
import ru.bogdsvn.matcher.errors.BadRequestException;
import ru.bogdsvn.matcher.store.LikeEmbeddedId;
import ru.bogdsvn.matcher.store.entities.LikeEntity;
import ru.bogdsvn.matcher.store.repositories.LikeRepository;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeRepository likeRepository;

    @Transactional
    public LikeDto saveLike(long profileId, long likedProfileId) {
        LikeEmbeddedId likeEmbeddedId = new LikeEmbeddedId(profileId, likedProfileId);
        likeRepository
                .findById(likeEmbeddedId)
                .ifPresent(like -> {
                    throw new BadRequestException("Пользователь уже поставил лайк");
                });

        likeRepository.save(LikeEntity.builder()
                .id(likeEmbeddedId)
                .build());

        return LikeDto.builder()
                .id(profileId)
                .likedId(likedProfileId)
                .build();
    }

    @Transactional
    public void deleteLike(long profileId, long likedProfileId) {
        LikeEmbeddedId likeEmbeddedId = new LikeEmbeddedId(profileId, likedProfileId);
        likeRepository.deleteById(likeEmbeddedId);
    }

    public boolean hasLike(long profileId, long likedProfileId) {
        return likeRepository.existsById(new LikeEmbeddedId(profileId, likedProfileId));
    }

    public boolean checkMatching(long profileId1, long profileId2) {
        boolean f1 = likeRepository.existsById(new LikeEmbeddedId(profileId1, profileId2));
        boolean f2 = likeRepository.existsById(new LikeEmbeddedId(profileId2, profileId1));
        return f1 && f2;
    }
}
