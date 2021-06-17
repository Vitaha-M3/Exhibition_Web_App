package com.exhibitions.service;

import java.text.ParseException;

public interface ExpositionAdminService {

    boolean addNewExposition(String name, Integer price, String dateTime, String period, String rooms) throws ParseException;

    void deleteExposition(Integer expo_id);
}
