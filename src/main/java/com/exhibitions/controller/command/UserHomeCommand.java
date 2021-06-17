package com.exhibitions.controller.command;

import com.exhibitions.dao.serviceDao.ExpositionDaoService;
import com.exhibitions.entity.Exposition;
import com.exhibitions.service.UserServiceDefault;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;


public class UserHomeCommand implements Command {
    UserServiceDefault userServiceDefault = new UserServiceDefault();
    private ExpositionDaoService expoDao = new ExpositionDaoService();
    public static final String PAGE = "page";
    public static final String USER_JSP = "/homepage/userhome.jsp";

    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException {
        Map<String,Object> map = new HashMap<>();
        ArrayList<String> themeExpo = (ArrayList<String>) userServiceDefault.getAllTheme();
        String filterByTheme = request.getParameter("themeExpoChange");
        String filterByDate = request.getParameter("filterDate");
        String sortPrice = request.getParameter("sortPrice");

        if(request.getParameter("buttonChoseTheme")!=null)
            request.setAttribute("choseTheme",true);

        if(filterByTheme==null) {
            map.put("catalog_exposition", expoDao.getAll());
        }else{
            List<Exposition> filteredExpoByTheme = userServiceDefault.filterByTheme(filterByTheme);
            request.setAttribute("filterTheme",true);
            map.clear();
            map.put("catalog_exposition", filteredExpoByTheme);
        }

        if(filterByDate!=""&&filterByDate!=null){
            List<Exposition> filterExpoByDate = userServiceDefault.filterByDate(filterByDate);
            if(filterExpoByDate.size() != 0){
                map.clear();
                map.put("catalog_exposition", filterExpoByDate);
            }
            request.setAttribute("errorMessageChoseAnotherDate",true);
        }

        //Сортировка таблицы по цене
        if(sortPrice != null){
            List<Exposition> sortedExpoPrice = userServiceDefault.filterByPrice((List<Exposition>) map.get("catalog_exposition"));
            map.clear();
            map.put("catalog_exposition",sortedExpoPrice);
            sortPrice =null;
        }
        map.put("themeExpo", themeExpo);
        map.put(PAGE, USER_JSP);
        return map;
    }
}
