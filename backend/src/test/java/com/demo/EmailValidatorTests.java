package com.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.demo.controllers.MyEmailValidator;

public class EmailValidatorTests {

    @Test
    void validateInvalidEmails() {
        assertEquals(MyEmailValidator.validate(""), false);
        assertEquals(MyEmailValidator.validate("email.com"), false); 
        assertEquals(MyEmailValidator.validate("@example.com"), false); 
        assertEquals(MyEmailValidator.validate("example@.com"), false); 
        assertEquals(MyEmailValidator.validate("example@com."), false); 
        assertEquals(MyEmailValidator.validate("example@com"), false);
    }

    @Test
    void validateValidEmails() {
        assertEquals(MyEmailValidator.validate("user@example.com"), true);
        assertEquals(MyEmailValidator.validate("my.email@domain.org"), true);
        assertEquals(MyEmailValidator.validate("name.surname@company.co"), true);
        assertEquals(MyEmailValidator.validate("test.email+filter@domain.com"), true);
    }
}