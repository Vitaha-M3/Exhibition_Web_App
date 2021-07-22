package com.exhibitions.service;

import com.exhibitions.dao.serviceDao.ExpositionDaoService;
import com.exhibitions.dao.serviceDao.OrderDaoService;
import com.exhibitions.dao.serviceDao.RoomDaoService;
import com.exhibitions.entity.Exposition;
import com.exhibitions.entity.Ticket;
import com.exhibitions.service.interfaceService.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserServiceDefault implements UserService {

    private ExpositionDaoService expoDao = new ExpositionDaoService();
    private OrderDaoService orderDao = new OrderDaoService();
    private static final Logger logger = LogManager.getLogger(UserServiceDefault.class);
    long countSort =0;

    @Override
    public List<Exposition> filterByTheme(String theme) throws SQLException, NamingException {
        return expoDao.findByName(theme);
    }

    @Override
    public List<String> getAllTheme() throws SQLException, NamingException {
        List<Exposition> allExpo = expoDao.getAll();
        List<String> expoTheme = new ArrayList<>();
        for (int i =0; i<allExpo.size(); i++){
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
            logger.log(Level.ERROR, e.getMessage());
            e.printStackTrace();
        }

        for(Exposition ex : allExpoByDate){
            Date start = null;
            try {
                start = formatter.parse(ex.getDate().toString());
            } catch (ParseException e) {
                logger.log(Level.ERROR, e.getMessage());
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
    public List<Ticket> getMyTickets(Integer user_id){
        return orderDao.getMyOrderForUser(user_id);
    }

    @Override
    public List<Exposition> sortByPrice(List<Exposition> expositions) {
        countSort++;
        if(countSort%2==0)
            expositions.sort(Comparator.comparingInt(Exposition::getPrice));
        else
        expositions.sort(Comparator.comparingInt(Exposition::getPrice).reversed());
      return expositions;
    }

    @Override
    public List<Exposition> getLimitExpo(Integer firstRow, Integer rowCount){
        return expoDao.getLimitRows(firstRow, rowCount);
    }

    @Override
    public Integer getCountAllExpo(){
        return expoDao.getCountAllRows();
    }

    public long getCountSort() {
        return countSort;
    }

    public void setCountSort(long countSort) {
        this.countSort = countSort;
    }
}
