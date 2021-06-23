package com.exhibitions.controller.validators;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class DateValidatorTest {


    @Test
    public void testValidateDateTrue() {
        boolean valid = DateValidator.validate("29.06.2021");
        assertTrue(valid);
    }

    @Test
    public void testValidateDateFaulse() {
        boolean valid = DateValidator.validate("01.05.2021");
        assertFalse(valid);
    }
}