package com.exhibitions.service;

import com.exhibitions.dao.serviceDao.ExpositionDaoService;
import com.exhibitions.entity.Exposition;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserServiceDefault implements UserService {
    ExpositionDaoService expoDao = new ExpositionDaoService();

    @Override
    public List<Exposition> filterByTheme(String theme) throws SQLException, NamingException {
        return expoDao.findByName(theme);
    }

    @Override
    public List<String> getAllTheme() throws SQLException, NamingException {
        List<Exposition> allExpo = expoDao.getAll();
        List<String> expoTheme = new ArrayList<>();
        for (int i =0; i<allExpo.size();i++){
            expoTheme.add(allExpo.get(i).getName());
        }
        Set<String> set = new HashSet<>(expoTheme);
        expoTheme.clear();
        expoTheme.addAll(set);
        return expoTheme;
    }

    @Override
    public List<Exposition> filterByDate(String date) {
        List<Exposition> allExpoByDate = expoDao.getAll();
        List<Exposition> filteredExpo = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateSearc = null;
        try {
            dateSearc = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for(Exposition ex : allExpoByDate){
            Date start = null;
            try {
                start = formatter.parse(ex.getDate().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date finish = ex.getPeriod();
            if(dateSearc.after(start)&&dateSearc.before(finish)){
                filteredExpo.add(ex);
            }else if (dateSearc.equals(start)||dateSearc.equals(finish)){
                filteredExpo.add(ex);
            }
        }
        return filteredExpo;
    }

    @Override
    public List<Exposition> filterByPrice(List<Exposition> expositions) throws SQLException, NamingException {
        expositions.sort((expositionItem, t1) -> {
            Integer price = expositionItem.getPrice();
            Integer price2 = t1.getPrice();
            return price.compareTo(price2);
        });
        return expositions;
    }

}
