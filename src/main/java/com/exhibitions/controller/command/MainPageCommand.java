package com.exhibitions.controller.command;

import com.exhibitions.dao.serviceDao.ExpositionDaoService;
import com.exhibitions.entity.User;
import com.exhibitions.service.UserServiceDefault;

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
    private static final String CATALOG_EXPO = "catalog_exposition";
    private static final String NO_OF_PAGES = "noOfPages";
    private static final String CURRENT_PAGE_TABLE = "currentPageTable";

    private final ExpositionDaoService expoDao = new ExpositionDaoService();
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
            map.put(CATALOG_EXPO, expoDao.getLimitRows(0,3));
            map.put(NO_OF_PAGES,Math.ceil((new UserServiceDefault().getCountAllExpo()*1.0/3)));
            map.put(CURRENT_PAGE_TABLE, 1);
        }
        return map;
    }

    private void initSession(Map<String, Object> map, User user) {
        String role = user.getAccess().toString().toLowerCase();
        map.put(PAGE, "/homepage/" + role + MAIN_JSP);
        if(role.equals("admin")){
            map.put(CATALOG_EXPO, expoDao.getAll());
        }else map.put(CATALOG_EXPO, expoDao.getLimitRows(0,3));
        map.put(NO_OF_PAGES,Math.ceil((new UserServiceDefault().getCountAllExpo()*1.0/3)));
        map.put(CURRENT_PAGE_TABLE, 1);
    }
}
