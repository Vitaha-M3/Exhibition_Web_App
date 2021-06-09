package com.exhibitions.dao;

import com.exhibitions.entity.Order;

import java.util.List;

public interface OrderDao {

    //get all list orders from table
    List<Order> getAllOrders();
}
