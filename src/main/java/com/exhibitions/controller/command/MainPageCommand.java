package com.exhibitions.controller.command;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MainPageCommand implements Command {
    private static final String MAIN_JSP = "/helloUser.jsp";
    private static final String PAGE = "page";
    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException {
        Map<String, Object> map = new HashMap<>();
        map.put(PAGE, MAIN_JSP);
        return map;
    }
}
