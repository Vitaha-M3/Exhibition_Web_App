package com.exhibitions.dao.serviceDao;

import com.exhibitions.dao.DBManager;
import com.exhibitions.dao.StatisticDao;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.exhibitions.constants.SQLConstants.*;

public class StatisticDaoService implements StatisticDao {
    private static final Logger logger = LogManager.getLogger(StatisticDaoService.class);

    //Returns an integer as the number of successful payments for all users
    @Override
    public Integer quantitySuccessPayed(){
        Integer quantity = null;
        Connection connection = null;
        try {
            connection = DBManager.getConnection();
            PreparedStatement getQuantity = connection.prepareStatement(FIND_QUANTITY_SUCCESS_PAYED);
            ResultSet set = getQuantity.executeQuery();
            if(set.next())
                quantity = set.getInt("col");
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            DBManager.closeConnection(connection);
        }
        return quantity;
    }

    //Returns an integer as the number of NO successful payments for all users
    @Override
    public Integer quantityNoSuccessPayed(){
        Connection con = null;
        Integer quantity = null;
        try {
            con = DBManager.getConnection();
            PreparedStatement getQuantity = con.prepareStatement(FIND_QUANTITY_NOT_SUCCESS_PAYED);
            ResultSet set = getQuantity.executeQuery();
            if(set.next())
                quantity = set.getInt("col");
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            DBManager.closeConnection(con);
        }
        return quantity;
    }

    //Return an long the sum of all successful payments
    @Override
    public Long sumAllSuccessPayed(){
        Connection con = null;
        Long sum = null;
        try {
            con = DBManager.getConnection();
            PreparedStatement getQuantity = con.prepareStatement(SUM_ALL_SUCCESS_PAYED);
            ResultSet set = getQuantity.executeQuery();
            if(set.next())
                sum = set.getLong("sum");
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            DBManager.closeConnection(con);
        }
        return sum;
    }

    //Returns a list of maps where each map is a string from the request in which the number of tickets purchased for the exhibition
    @Override
    public List<Map<String,Object>> purchasedTicketsOnExpo(){
        Connection connector = null;
        List<Map<String,Object>> list = new ArrayList<>();
        try {
            connector = DBManager.getConnection();
            PreparedStatement pst = connector.prepareStatement(SHOW_QUANTITY_PURCHASED_TICKETS_ON_EXPOSITION);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                Map<String,Object> map = new HashMap<>();
                map.put("expoId", rs.getInt(1));
                map.put("name", rs.getString(2));
                map.put("dateOfAnd", rs.getDate(3));
                map.put("countBuyTickets", rs.getInt(4));
                map.put("sumBilling", rs.getInt(5));
                list.add(map);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            DBManager.closeConnection(connector);
        }
        return list;
    }

    //Returns statistics of visits of each user
    @Override
    public List<Map<String, Object>> userStatistic(){
        Connection connect = null;
        List<Map<String, Object>> userList = new ArrayList<>();
        try {
            connect = DBManager.getConnection();
            PreparedStatement prSt = connect.prepareStatement(SHOW_USERS_STATISTIC);
            ResultSet resultSet = prSt.executeQuery();
            while (resultSet.next()){
                Map<String, Object> map = new HashMap<>();
                map.put("userId", resultSet.getInt(1));
                map.put("name", resultSet.getString(2));
                map.put("email", resultSet.getString(3));
                map.put("purchasedTickets", resultSet.getInt(4));
                map.put("visitThisExposition", resultSet.getString(5));
                map.put("sumBuy", resultSet.getInt(6));
                userList.add(map);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            DBManager.closeConnection(connect);
        }
        return userList;
    }
}
