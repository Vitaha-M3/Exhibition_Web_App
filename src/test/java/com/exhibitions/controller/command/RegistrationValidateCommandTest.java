package com.exhibitions.controller.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class RegistrationValidateCommandTest {
    @Test
    public void testValidate() {
        assertEquals(0, RegistrationCommand.validate("Login", "jane.doe@example.org", "Name", "iloveyou").size());
    }

    @Test
    public void testValidate2() {
        List<String> actualValidateResult = RegistrationCommand.validate("", "jane.doe@example.org", "Name", "iloveyou");
        assertEquals(1, actualValidateResult.size());
        assertEquals("The Login field is required!", actualValidateResult.get(0));
    }

    @Test
    public void testValidate3() {
        List<String> actualValidateResult = RegistrationCommand.validate("Login", "Email", "Name", "iloveyou");
        assertEquals(1, actualValidateResult.size());
        assertEquals("Email entered incorrectly!", actualValidateResult.get(0));
    }

    @Test
    public void testValidate4() {
        List<String> actualValidateResult = RegistrationCommand.validate("Login", "jane.doe@example.org", "", "iloveyou");
        assertEquals(1, actualValidateResult.size());
        assertEquals("The Name field is required!", actualValidateResult.get(0));
    }

    @Test
    public void testValidate5() {
        List<String> actualValidateResult = RegistrationCommand.validate("Login", "jane.doe@example.org", "Name", "");
        assertEquals(1, actualValidateResult.size());
        assertEquals("Password is required!", actualValidateResult.get(0));
    }
}

