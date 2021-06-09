package com.exhibitions.controller.command;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class LogOutCommand implements Command {
    private static final String PAGE = "page";
    private static final String INDEX_JSP = "/index.jsp";

    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException {
        request.getSession().invalidate();
        Map<String, Object> map = new HashMap<>();
        map.put(PAGE, INDEX_JSP);
        return map;
    }
}
