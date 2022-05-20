package com.example.zmusic.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Objects;

public class EnumExistValidator implements ConstraintValidator<EnumExist, String> {
    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(EnumExist enumExist) {
        enumClass = enumExist.enumClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }

        Enum<?>[] enums = enumClass.getEnumConstants();
        return Arrays.stream(enums)
                .map(Enum::name)
                .anyMatch(name -> Objects.equals(name, value));
    }
}
