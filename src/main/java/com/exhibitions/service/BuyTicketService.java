package com.exhibitions.service;

import com.exhibitions.entity.Exposition;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Optional;

public interface BuyTicketService {
    Optional<Exposition> findExpoByID (String id) throws SQLException, NamingException;

    void billingByTicket(Integer billing, Integer user_id, Integer status_id, Integer exposition_id);

    boolean buyTicketFinal(Integer user_id, Integer exposition_id);


}
