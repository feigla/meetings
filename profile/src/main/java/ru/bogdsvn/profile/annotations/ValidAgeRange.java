package ru.bogdsvn.profile.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.bogdsvn.profile.utils.AgeRangeValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AgeRangeValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAgeRange {
    String message() default "Неправильно указан диапазон";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
