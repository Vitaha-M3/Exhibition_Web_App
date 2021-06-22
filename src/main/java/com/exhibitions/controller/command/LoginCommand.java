package com.exhibitions.controller.command;

import com.exhibitions.entity.User;
import com.exhibitions.service.LoginServiceDefault;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LoginCommand implements Command {
    private final LoginServiceDefault loginService = new LoginServiceDefault();
    public static final String PAGE = "page";
    public static final String INDEX_JSP = "/index.jsp";
    public static final String ERR_MESSAGE = "errorMessage";
    public static final Object ERROR_MESSAGE = "Incorrect data, please try again";
    private static final String CATALOG_EXPO = "catalog_exposition";
    private static final String NO_OF_PAGES = "noOfPages";
    private static final String CURRENT_PAGE_TABLE = "currentPageTable";


    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException {
            Map<String, Object> map = new HashMap<>();
        String username = request.getParameter("login");
        String password = request.getParameter("password");
        Map<String, Object> loginMap = loginService.login(username, password);
        Optional<User> user =  (Optional<User>) loginMap.get("user");
        if(user.isPresent()) {
            initSession(request, map, loginMap, user.get());
        } else {
            map.put(PAGE, INDEX_JSP);
            if(username!=null){
                request.setAttribute("errorLogin", true);
                map.put(ERR_MESSAGE, ERROR_MESSAGE);
            }
        }
        return map;
    }

    private void initSession(HttpServletRequest request, Map<String, Object> map, Map<String, Object> loginMap, User user) {
        String role = user.getAccess().toString().toLowerCase();
        map.put(PAGE, "/homepage/" + role + "home.jsp");
        map.put(CATALOG_EXPO, loginMap.get(CATALOG_EXPO));
        map.put(NO_OF_PAGES, loginMap.get(NO_OF_PAGES));
        map.put(CURRENT_PAGE_TABLE,1);
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(-1);
        session.setAttribute("user", user);
        session.setAttribute("userLoggedIn", true);
    }
}
