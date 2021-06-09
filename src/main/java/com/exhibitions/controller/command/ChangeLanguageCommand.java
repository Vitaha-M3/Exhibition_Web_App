package com.exhibitions.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ChangeLanguageCommand implements Command {
    private static final String LANGUAGE = "language";
    public static final String PAGE = "page";
    public static final String INDEX_JSP = "/index.jsp";

    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        String language = request.getParameter(LANGUAGE);
        request.getSession().setAttribute(LANGUAGE, language);
        Map<String, Object> map = new HashMap<>();
        map.put(PAGE, INDEX_JSP);
        return map;
    }
}
