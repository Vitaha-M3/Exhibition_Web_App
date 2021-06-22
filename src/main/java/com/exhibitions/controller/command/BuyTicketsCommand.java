package com.exhibitions.controller.command;

import com.exhibitions.entity.Exposition;
import com.exhibitions.entity.User;
import com.exhibitions.service.BuyTicketServiceDefault;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BuyTicketsCommand implements Command {
    BuyTicketServiceDefault buyTicket = new BuyTicketServiceDefault();
    private static final String PAGE = "page";
    private static final String BUY_JSP = "/homepage/buyTicket.jsp";
    private static final String THANK_BUY = "/homepage/thankforbuy.jsp";

    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException, IOException {
        Map<String,Object> map = new HashMap<>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int ticketExpoId = 0;
        Optional<Exposition> ticketOnExpo;

        if(request.getParameter("itemToBuy")!=null){
            ticketOnExpo = buyTicket.findExpoByID(request.getParameter("itemToBuy"));
            buyTicket.billingByTicket(ticketOnExpo.get().getPrice(),user.getId(),1, ticketOnExpo.get().getId());
            request.setAttribute("ticketOnExpo",ticketOnExpo.get());
            map.put(PAGE, BUY_JSP);
        }

        map.put("user",user);

        if(request.getParameter("finalBuy")!=null){
            ticketExpoId = Integer.parseInt(request.getParameter("expoID"));
            if(buyTicket.buyTicketFinal(user.getId(), ticketExpoId)) {
                map.clear();
                map.put(PAGE, THANK_BUY);
            }
        }
        return map;
    }
}
