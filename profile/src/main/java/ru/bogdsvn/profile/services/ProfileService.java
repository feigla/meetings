package ru.bogdsvn.profile.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bogdsvn.profile.store.entites.ProfileEntity;
import ru.bogdsvn.profile.store.repositories.ProfileRepository;


@Log4j2
@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    @Transactional
    public void saveProfile(String id) {
        profileRepository.save(
                ProfileEntity
                        .builder()
                        .id(id)
                        .build()
        );
    }
}
