package com.exhibitions.dao.serviceDao;

import com.exhibitions.dao.DBManager;
import com.exhibitions.dao.OrderDao;
import com.exhibitions.entity.Order;
import com.exhibitions.entity.OrderStatus;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.exhibitions.constants.SQLConstants.*;

public class OrderDaoService implements OrderDao {
    private static final Logger logger = LogManager.getLogger(OrderDaoService.class);

    @Override
    public List<Order> getAllOrders() {
        Connection con = null;
        List<Order> orders = new ArrayList<>();
        try {
            con = DBManager.getConnection();
            Statement getAll = con.createStatement();
            ResultSet set = getAll.executeQuery(GET_ALL_ORDERS);
            initList(set, orders);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            DBManager.closeConnection(con);
        }
        return orders;
    }

    @Override
    public void insertOrder(Integer billing, Integer user_id, Integer status_id, Integer exposition_id) {
        Connection con = null;
        try {
            con = DBManager.getConnection();
            con.setAutoCommit(false);
            PreparedStatement insertSt = con.prepareStatement(INSERT_ORDER);
            insertSt.setInt(1, billing);
            insertSt.setInt(2, user_id);
            insertSt.setInt(3, status_id);
            insertSt.setInt(4, exposition_id);
            insertSt.executeUpdate();
            con.commit();
        }catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            DBManager.rollback(con);
        } finally {
            DBManager.closeConnection(con);
        }
    }

    @Override
    public boolean updateOrder(Integer id, Integer status_id, Integer user_id, Integer exposition_id) {
        Connection con = null;
        try {
            con = DBManager.getConnection();
            con.setAutoCommit(false);
            PreparedStatement insertSt = con.prepareStatement(UPDATE_ORDER);
            insertSt.setInt(1, id);
            insertSt.setInt(2, status_id);
            insertSt.setInt(3, user_id);
            insertSt.setInt(4, exposition_id);
            insertSt.executeUpdate();
            con.commit();
            return true;
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            DBManager.rollback(con);
            return false;
        } finally {
            DBManager.closeConnection(con);
        }
    }

    @Override
    public Integer findOrderIdByUserId(Integer user_id, Integer status_id, Integer exposition_id){
        Connection con = null;
        Integer orderId = 0;
        try {
            con = DBManager.getConnection();
            PreparedStatement selectStatement = con.prepareStatement(FIND_ORDER_ID_BY_USER);
            selectStatement.setInt(1, user_id);
            selectStatement.setInt(2, status_id);
            selectStatement.setInt(3, exposition_id);
            ResultSet set = selectStatement.executeQuery();
            if(set.next()) {
                orderId = set.getInt("id");
            }
        }catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            DBManager.closeConnection(con);
        }
        return orderId;
    }


    private void initList(ResultSet set, List<Order> orders) throws SQLException {
        while (set.next()){
            Order order = new Order();
            order.setId(set.getInt("id"));
            order.setBilling(set.getInt("billing"));
            order.setUser(set.getInt("user-id"));
            order.setOrderStatus_id(set.getInt("status-id"));
            order.setExposition(set.getInt("exposition-id"));
            orders.add(order);
        }
    }
}
