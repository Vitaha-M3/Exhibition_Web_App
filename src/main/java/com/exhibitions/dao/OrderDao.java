package com.exhibitions.dao;

import com.exhibitions.entity.Order;

import java.util.List;

public interface OrderDao {

    //get all list orders from table
    List<Order> getAllOrders();

    void insertOrder(Integer billing, Integer user_id, Integer status_id, Integer exposition_id);

    boolean updateOrder(Integer id, Integer status_id, Integer user_id, Integer exposition_id);

    Integer findOrderIdByUserId(Integer user_id, Integer status_id, Integer exposition_id);
}
