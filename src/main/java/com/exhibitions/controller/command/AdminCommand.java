package com.exhibitions.controller.command;

import com.exhibitions.dao.serviceDao.ExpositionDaoService;
import com.exhibitions.entity.User;
import com.exhibitions.service.AdminStatisticServiceDefault;
import com.exhibitions.service.ExpositionAdminServiceDefault;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminCommand implements Command {

    ExpositionAdminServiceDefault expoService = new ExpositionAdminServiceDefault();
    AdminStatisticServiceDefault statisticService = new AdminStatisticServiceDefault();
    ExpositionDaoService expoDao = new ExpositionDaoService();
    private static final String PAGE = "page";
    private static final String ADMIN_JSP = "/homepage/adminhome.jsp";
    private static final String ACCESS_FAIL = "/errors/accessfail.jsp";

    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException, UnsupportedEncodingException, ParseException {
        Map<String, Object> map = new HashMap<>();
        User admin = (User)request.getSession().getAttribute("user");

        /**
         * For statistic block
         */
        //show statistic
        if(request.getParameter("showStatistic")!=null){
            request.setAttribute("showMeStatistic", true);
            request.getSession().setAttribute("showMeStatistic", true);
        }

        //for button show finance statistic
        if(request.getParameter("showFinStatistic")!=null){
            request.setAttribute("showFinStatistic", true);
            Integer quantitySuccessPayed = statisticService.getSuccessPayed();
            Integer quantityNoSuccessPayed = statisticService.getNoSuccessPayed();
            Long sumAllSuccessPayed = statisticService.getSumAllSuccessPayed();
            map.put("quantitySuccessPay", quantitySuccessPayed);
            map.put("quantityNoSuccessPay", quantityNoSuccessPayed);
            map.put("sumAllSuccessPay", sumAllSuccessPayed);
        }

        //for button show quantity purchased tickets on exposition
        if(request.getParameter("showQuantityTickForExpo")!=null){
            request.setAttribute("showQuantityTickForExpo",true);
            List<Map<String, Object>> listMap;
            listMap = statisticService.getPurchasedTickOnExpo();
            request.setAttribute("tablePurchasedTickets", listMap);
        }

        //for button show statistic on users
        if(request.getParameter("showForUserStatistic")!=null){
            request.setAttribute("showForUserStatistic",true);
            List<Map<String, Object>> listUsers = statisticService.getStatisticOnUsers();
            request.setAttribute("tableUsersStatistic", listUsers);
        }


        /**
         * For buttons left block
         */
        //add new exposition
        if(request.getParameter("addNewExposition")!=null){
            String dateTime = request.getParameter("dateTime");
            String period = request.getParameter("period");
            String[] rooms = request.getParameterValues("rooms");
            Integer price = Integer.parseInt(request.getParameter("price"));
            String newTheme = request.getParameter("theme");
            System.out.println(newTheme);
            StringBuilder getAllRooms = new StringBuilder();
            for (String s: rooms) {
                getAllRooms.append(s).append(", ");
            }
            if(getAllRooms.length()>0)
                getAllRooms.deleteCharAt(getAllRooms.length()-2);

            boolean addNewExpo = expoService.addNewExposition(newTheme,price, dateTime, period, getAllRooms.toString());
            if(addNewExpo){
                map.put("successAdded", "SUCCESS ADDED");
            }else map.put("successAdded", "NO EXPOSITION HAS BEEN ADDED");
        }

        //delete exposition
        if(request.getParameter("itemToDel")!=null) {
            Integer itemToDel = Integer.parseInt(request.getParameter("itemToDel"));
            expoService.deleteExposition(itemToDel);
            request.setAttribute("deleteExpoVisible",true);
        }

        //visible button delete
        if(request.getParameter("deleteExpo")!=null){
            request.setAttribute("deleteExpoVisible",true);
            request.getSession().removeAttribute("showMeStatistic");
        }

        //show table add exposition
        if(request.getParameter("addExpo")!=null){
            request.setAttribute("addExpoVisible",true);
        }


        /**
         * Check access
         */
        if(admin!=null && admin.getAccess().toString().toLowerCase().equals("admin")) {
            map.put(PAGE, ADMIN_JSP);
        }else {
            map.put(PAGE, ACCESS_FAIL);
            return map;
        }
        map.put("catalog_exposition", expoDao.getAll());
        return map;
    }
}
