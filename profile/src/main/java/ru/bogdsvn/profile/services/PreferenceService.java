package ru.bogdsvn.profile.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bogdsvn.profile.dtos.PreferenceDto;
import ru.bogdsvn.profile.errors.BadRequestException;
import ru.bogdsvn.profile.errors.NotFoundException;
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
    public PreferenceDto fillPreference(PreferenceDto preferenceDto, long id) {
        preferenceRepository
                .findById(id)
                .ifPresent((preference) -> {
                    throw new BadRequestException("Интересы уже заполнены");
                });

        ProfileEntity profile = profileRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));

        PreferenceEntity preferenceEntity = PreferenceEntity.builder()
                .gender(Gender.valueOf(preferenceDto.getGender()))
                .ageLowerBound(preferenceDto.getAgeLowerBound())
                .ageUpperBound(preferenceDto.getAgeUpperBound())
                .profile(profile)
                .build();

        preferenceRepository.save(preferenceEntity);

        return preferenceFactory.makePreferenceDto(preferenceEntity);
    }

    @Transactional
    public PreferenceDto updatePreference(PreferenceDto preferenceDto, long id) {
        PreferenceEntity preferenceEntity = preferenceRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Интересы не заполнены"));

        preferenceEntity.setGender(Gender.valueOf(preferenceDto.getGender()));
        preferenceEntity.setAgeLowerBound(preferenceDto.getAgeLowerBound());
        preferenceEntity.setAgeUpperBound(preferenceDto.getAgeUpperBound());

        preferenceRepository.save(preferenceEntity);

        return preferenceFactory.makePreferenceDto(preferenceEntity);
    }

    public PreferenceDto getPreference(long id) {
        return preferenceFactory.makePreferenceDto(
                preferenceRepository
                        .findById(id)
                        .orElseThrow(() -> new NotFoundException("Интересеы не заполнены"))
                );
    }
}
