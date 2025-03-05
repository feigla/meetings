package ru.bogdsvn.profile.junit;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.bogdsvn.profile.dtos.PreferenceDto;

import java.util.Set;

public class ValidAgeRangeTest {
    Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void givenCorrectPreferenceDto_ShouldNotThrowException() {
        PreferenceDto preferenceDto = PreferenceDto.builder()
                .gender("MALE")
                .ageLowerBound(18)
                .ageUpperBound(80)
                .build();
        Set<ConstraintViolation<PreferenceDto>> violations = validator.validate(preferenceDto);
        Assertions.assertTrue(violations.isEmpty());
    }

    @CsvSource({"50,30", "30,29", "80, 18"})
    @ParameterizedTest
    void givenIncorrectPreferenceDto_ShouldThrowException(int ageLowerBound, int ageUpperBound) {
        PreferenceDto preferenceDto = PreferenceDto.builder()
                .gender("MALE")
                .ageLowerBound(ageLowerBound)
                .ageUpperBound(ageUpperBound)
                .build();
        Set<ConstraintViolation<PreferenceDto>> violations = validator.validate(preferenceDto);
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertTrue(violations.iterator().next().getMessage().equals("Неправильно указан диапазон"));
    }
}
