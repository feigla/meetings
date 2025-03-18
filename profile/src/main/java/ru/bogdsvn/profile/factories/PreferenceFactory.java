package ru.bogdsvn.profile.factories;

import org.springframework.stereotype.Component;
import ru.bogdsvn.profile.dtos.PreferenceDto;
import ru.bogdsvn.profile.store.entites.PreferenceEntity;


@Component
public class PreferenceFactory {
    public PreferenceDto makePreferenceDto(PreferenceEntity entity) {
        return PreferenceDto
                .builder()
                .id(entity.getId())
                .gender(entity.getGender().value)
                .ageLowerBound(entity.getAgeLowerBound())
                .ageUpperBound(entity.getAgeUpperBound())
                .build();
    }
}
