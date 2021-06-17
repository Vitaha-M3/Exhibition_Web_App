package com.exhibitions.controller.command;

import com.exhibitions.dao.serviceDao.ExpositionDaoService;
import com.exhibitions.entity.User;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MainPageCommand implements Command {
    private static final String MAIN_JSP = "home.jsp";
    private static final String PAGE = "page";
    private ExpositionDaoService expoDao = new ExpositionDaoService();
    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException {
        Map<String, Object> map = new HashMap<>();
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        Optional<User> presentUser = Optional.ofNullable(user);
        if(presentUser.isPresent()) {
            initSession(map, presentUser.get());
        } else {
            map.put(PAGE, "/homepage/userhome.jsp");
            map.put("catalog_exposition", expoDao.getAll());
        }
        return map;
    }

    private void initSession(Map<String, Object> map, User user) {
        String role = user.getAccess().toString().toLowerCase();
        map.put(PAGE, "/homepage/" + role + MAIN_JSP);
        map.put("catalog_exposition", expoDao.getAll());
    }
}
