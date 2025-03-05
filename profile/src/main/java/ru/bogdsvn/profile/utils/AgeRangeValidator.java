package ru.bogdsvn.profile.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.bogdsvn.profile.annotations.ValidAgeRange;
import ru.bogdsvn.profile.dtos.PreferenceDto;

public class AgeRangeValidator implements ConstraintValidator<ValidAgeRange, PreferenceDto>{
    @Override
    public boolean isValid(PreferenceDto dto, ConstraintValidatorContext constraintValidatorContext) {
        if (dto.getAgeLowerBound() == null || dto.getAgeUpperBound() == null) {
            return true;
        }
        return dto.getAgeLowerBound() < dto.getAgeUpperBound();
    }
}
