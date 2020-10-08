package com.intro.users.demo.models;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("userValidator")
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "name", "required.name","Field Name is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "title", "required.title","Field Title is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "secureNumber", "required.secureNumber","Field Secure Number is required.");
    }
}
