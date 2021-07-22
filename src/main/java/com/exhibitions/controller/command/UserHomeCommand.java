package com.exhibitions.controller.command;

import com.exhibitions.dao.serviceDao.ExpositionDaoService;
import com.exhibitions.entity.Exposition;
import com.exhibitions.entity.User;
import com.exhibitions.service.UserServiceDefault;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.*;


public class UserHomeCommand implements Command {

    private final UserServiceDefault userServiceDefault = new UserServiceDefault();
    private final ExpositionDaoService expoDao = new ExpositionDaoService();
    private static final String PAGE = "page";
    private static final String USER_JSP = "/homepage/userhome.jsp";
    private static final String CATALOG_EXPO = "catalog_exposition";
    private static final String NO_OF_PAGES = "noOfPages";
    private static final String CURRENT_PAGE_TABLE = "currentPageTable";
    private static final String SORTED_CATALOG_EXPO = "sortedCatalogExposition";
    private static final String PAGE_TABLE = "pageTable";


    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException {
        Map<String,Object> map = new HashMap<>();
        ArrayList<String> themeExpo = (ArrayList<String>) userServiceDefault.getAllTheme();
        String filterByTheme = request.getParameter("themeExpoChange");
        String filterByDate = request.getParameter("filterDate");
        String sortPrice = request.getParameter("sortPrice");
        List<Exposition> expositionList;

        if(request.getParameter("buttonChoseTheme")!=null)
            request.setAttribute("choseTheme",true);

        //Filter by choose theme
        if(filterByTheme==null) {
            pagination(expoDao, map,request,userServiceDefault);
        }else{
            expositionList = userServiceDefault.filterByTheme(filterByTheme);
            request.setAttribute("filterTheme",true);
            map.put(CATALOG_EXPO, expositionList);
        }

        //Filter table by date
        if(filterByDate!="" && filterByDate!=null){
                    expositionList = userServiceDefault.filterByDate(filterByDate);
            if(expositionList.size() != 0){
                map.clear();
                map.put(CATALOG_EXPO, expositionList);
            }
            else request.setAttribute("errorMessageChoseAnotherDate",true);
        }

        //Sorted table by price
        if(sortPrice != null){
            request.getSession(true).setAttribute(SORTED_CATALOG_EXPO, userServiceDefault.sortByPrice(expoDao.getAll()));
            pagination(expoDao,map,request,userServiceDefault);
        }

        //Get my tickets
        if(request.getParameter("getMyTickets")!=null){
            Integer userId = ((User) request.getSession().getAttribute("user")).getId();
            request.setAttribute("showMyTickets", true);
            map.put("catalogMyTickets",userServiceDefault.getMyTickets(userId));
        }

        //Default put in map
        map.put("themeExpo", themeExpo);
        map.put(PAGE, USER_JSP);
        return map;
    }

    private void pagination(ExpositionDaoService expoDao, Map<String, Object> map, HttpServletRequest request, UserServiceDefault userService){
        int pageTable = 1;
        int recordsPerPage = 3;
        int noOfRecords = userService.getCountAllExpo();
        int noOfPages = (int)Math.ceil(noOfRecords*1.0/recordsPerPage);
        int lastTo;
        List<Exposition> expo;

        if(request.getParameter(PAGE_TABLE) != null)
            pageTable = Integer.parseInt(request.getParameter(PAGE_TABLE));

        if(((pageTable - 1) * recordsPerPage) + recordsPerPage>noOfRecords)
            lastTo = noOfRecords;
        else lastTo = ((pageTable - 1) * recordsPerPage) + recordsPerPage;

        if(request.getSession().getAttribute(SORTED_CATALOG_EXPO)!=null){
            List<Exposition> sortedList = (List<Exposition>) request.getSession().getAttribute(SORTED_CATALOG_EXPO);
            expo = sortedList.subList((pageTable - 1) * recordsPerPage,lastTo);
        }
        else expo = expoDao.getAll().subList((pageTable - 1) * recordsPerPage,lastTo);

        map.put(CATALOG_EXPO, expo);
        map.put(NO_OF_PAGES, noOfPages);
        map.put(CURRENT_PAGE_TABLE, pageTable);
    }
}
