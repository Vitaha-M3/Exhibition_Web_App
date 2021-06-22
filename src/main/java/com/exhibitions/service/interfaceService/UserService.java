package com.exhibitions.service.interfaceService;

import com.exhibitions.entity.Exposition;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface UserService {
    List<Exposition> filterByTheme(String theme) throws SQLException, NamingException;
    List<String> getAllTheme() throws SQLException, NamingException;
    List<Exposition> filterByDate(String date) throws SQLException, NamingException, ParseException;
    List<Exposition> sortByPrice(List<Exposition> expositions) throws SQLException, NamingException;
    List<Exposition> getLimitExpo(Integer firstRow, Integer rowCount);
    Integer getCountAllExpo();
}
