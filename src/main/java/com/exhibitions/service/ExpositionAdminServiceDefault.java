package com.exhibitions.service;

import com.exhibitions.controller.validators.DateValidator;
import com.exhibitions.dao.serviceDao.ExpositionDaoService;
import com.exhibitions.service.interfaceService.ExpositionAdminService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ExpositionAdminServiceDefault implements ExpositionAdminService {
    ExpositionDaoService expoDaoService = new ExpositionDaoService();

    @Override
    public boolean addNewExposition(String name, Integer price, String dateTime, String period, String rooms) throws ParseException {
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime);
        String swap = localDateTime.format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm"));
        Date newDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(swap);
        Date newPeriod = new SimpleDateFormat("yyyy-MM-dd").parse(period);

        if(newPeriod.after(newDateTime) && DateValidator.validate(swap)){
            expoDaoService.insertExposition(name, price, newDateTime, newPeriod, rooms);
            return true;
        }
        return false;
    }

    @Override
    public void deleteExposition(Integer expo_id) {
        expoDaoService.deleteExposition(expo_id);
    }
}
