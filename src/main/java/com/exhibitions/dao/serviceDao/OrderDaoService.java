package com.exhibitions.dao.serviceDao;

import com.exhibitions.dao.DBManager;
import com.exhibitions.dao.OrderDao;
import com.exhibitions.entity.Order;
import com.exhibitions.entity.OrderStatus;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.exhibitions.constants.SQLConstants.GET_ALL_ORDERS;

public class OrderDaoService implements OrderDao {
    private static final Logger logger = LogManager.getLogger();

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

    private void initList(ResultSet set, List<Order> orders) throws SQLException {
        while (set.next()){
            Order order = new Order();
            order.setId(set.getInt("id"));
            order.setBilling(set.getInt("billing"));
            order.setUser(new UserDaoService().findById(set.getInt("user-id")));
            order.setOrderStatus(new OrderStatus(set.getInt("status-id")));
            order.setExposition(new ExpositionDaoService().findById(set.getInt("exposition-id")));
            orders.add(order);
        }
    }
}
