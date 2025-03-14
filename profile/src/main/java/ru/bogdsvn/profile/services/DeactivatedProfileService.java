package ru.bogdsvn.profile.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bogdsvn.profile.dtos.ProfileStatusDto;
import ru.bogdsvn.profile.errors.BadRequestException;
import ru.bogdsvn.profile.store.entites.DeactivatedProfileEntity;
import ru.bogdsvn.profile.store.repositories.DeactivatedProfileRepository;

@RequiredArgsConstructor
@Service
public class DeactivatedProfileService {
    private final DeactivatedProfileRepository profileRepository;

    public ProfileStatusDto getStatus(long id) {
        ProfileStatusDto status = ProfileStatusDto.builder().isActive(true).build();
        profileRepository
                .findById(id)
                .ifPresent(profile -> {
                    if (profile.getStatus().equals(DeactivatedProfileEntity.Status.FINISHED)) {
                        status.setActive(false);
                    }
                });
        return status;
    }

    @Transactional
    public void setProfileDeactivated(long id) {
        profileRepository
                .findById(id)
                .ifPresent((p) -> {
                    throw new BadRequestException("Профиль выключен");
                });
        profileRepository.save(
                DeactivatedProfileEntity.builder()
                        .id(id)
                        .build()
        );
    }

    @Transactional
    public void setStatusDeactivatedProfileFinished(long id) {
        profileRepository
                .findById(id)
                .ifPresent((p) -> {
                    p.setStatus(DeactivatedProfileEntity.Status.FINISHED);
                });
    }
}
