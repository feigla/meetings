package ru.bogdsvn.profile.factories;

import org.springframework.stereotype.Component;
import ru.bogdsvn.profile.dtos.BioDto;
import ru.bogdsvn.profile.store.entites.BioEntity;

@Component
public class BioFactory {
    public BioDto makeBioDto(BioEntity entity) {
        return BioDto
                .builder()
                .age(entity.getAge())
                .name(entity.getName())
                .description(entity.getDescription())
                .gender(entity.getGender().value)
                .build();
    }
}
