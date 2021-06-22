package com.exhibitions.service;

import com.exhibitions.dao.serviceDao.ExpositionDaoService;
import com.exhibitions.dao.serviceDao.OrderDaoService;
import com.exhibitions.entity.Exposition;
import com.exhibitions.service.interfaceService.BuyTicketService;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Optional;

public class BuyTicketServiceDefault implements BuyTicketService {
    ExpositionDaoService expoDaoService = new ExpositionDaoService();
    OrderDaoService orderDaoService = new OrderDaoService();

    @Override
    public Optional<Exposition> findExpoByID(String id) throws SQLException, NamingException {
        Integer idExpo = Integer.parseInt(id);
        return expoDaoService.findById(idExpo);
    }

    @Override
    public void billingByTicket(Integer billing, Integer user_id, Integer status_id, Integer exposition_id){
        orderDaoService.insertOrder(billing, user_id ,status_id, exposition_id);
    }

    @Override
    public boolean buyTicketFinal(Integer user_id, Integer exposition_id){
        Integer order_id = orderDaoService.findOrderIdByUserId(user_id, 1, exposition_id);
        return orderDaoService.updateOrder(order_id, 1, user_id, exposition_id);
    }
}
