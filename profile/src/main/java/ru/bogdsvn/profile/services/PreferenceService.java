package ru.bogdsvn.profile.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bogdsvn.profile.dtos.PreferenceDto;
import ru.bogdsvn.profile.factories.PreferenceFactory;
import ru.bogdsvn.profile.store.entites.PreferenceEntity;
import ru.bogdsvn.profile.store.entites.ProfileEntity;
import ru.bogdsvn.profile.store.repositories.PreferenceRepository;
import ru.bogdsvn.profile.store.repositories.ProfileRepository;
import ru.bogdsvn.profile.utils.Gender;

@Log4j2
@RequiredArgsConstructor
@Service
public class PreferenceService {
    private final PreferenceFactory preferenceFactory;
    private final ProfileRepository profileRepository;
    private final PreferenceRepository preferenceRepository;

    @Transactional
    public PreferenceDto fillPreference(PreferenceDto preferenceDto, Long id) {
        ProfileEntity profile = profileRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        PreferenceEntity preferenceEntity = preferenceRepository.findById(id).orElse(
                PreferenceEntity
                        .builder()
                        .gender(Gender.valueOf(preferenceDto.getGender()))
                        .ageLowerBound(preferenceDto.getAgeLowerBound())
                        .ageUpperBound(preferenceDto.getAgeUpperBound())
                        .build()
        );

        preferenceEntity.setProfile(profile);
        preferenceRepository.save(preferenceEntity);

        return preferenceFactory.makePreferenceDto(preferenceEntity);
    }
}
