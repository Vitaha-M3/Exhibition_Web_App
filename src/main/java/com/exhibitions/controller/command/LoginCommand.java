package com.exhibitions.controller.command;

import com.exhibitions.entity.User;
import com.exhibitions.service.LoginServiceDefault;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final LoginServiceDefault loginService = new LoginServiceDefault();
    public static final String PAGE = "page";
    public static final String INDEX_JSP = "/index.jsp";
    public static final String ERR_MESSAGE = "errorMessage";
    public static final Object ERROR_MESSAGE = "errorMessage";

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
            //map.put(ERR_MESSAGE, ERROR_MESSAGE);
        }
        return map;
    }

    private void initSession(HttpServletRequest request, Map<String, Object> map, Map<String, Object> loginMap, User user) {
        String role = user.getAccess().toString().toLowerCase();
        map.put(PAGE, "/homepage/" + role + "home.jsp");
        map.put("catalog_exposition", loginMap.get("catalog_exposition"));
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(-1);
        session.setAttribute("user", user);
        session.setAttribute("userLoggedIn", true);
    }
}
