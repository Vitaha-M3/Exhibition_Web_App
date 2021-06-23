package com.exhibitions.controller.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ValidatorInputTest {
    @Test
    public void testValidateMail() {
        assertTrue(ValidatorInput.validateMail("jane.doe@example.org"));
    }

    @Test
    public void testValidateString() {
        assertTrue(ValidatorInput.validateString("value"));
        assertFalse(ValidatorInput.validateString(null));
        assertFalse(ValidatorInput.validateString(""));
    }
}

