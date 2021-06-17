package com.exhibitions.controller.validators;

import java.time.LocalDate;

public class DateValidator {
    public static boolean validate(String date) {
        return date.compareTo(LocalDate.now().toString()) >= 0;
    }
}
