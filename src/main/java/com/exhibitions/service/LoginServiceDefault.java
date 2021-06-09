package com.exhibitions.service;

import com.exhibitions.dao.serviceDao.ExpositionDaoService;
import com.exhibitions.dao.serviceDao.UserDaoService;
import com.exhibitions.entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LoginServiceDefault implements LoginService {
    private UserDaoService userDao = new UserDaoService();
    private ExpositionDaoService expoDao = new ExpositionDaoService();

    @Override
    public Map<String, Object> login(String login, String password) {
        Optional<User> user = userDao.findByLogin(login);
        user = user.filter(user1 -> password.equals(user1.getPassword()));
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
//        User usermy = user.orElse(null);
//        if(usermy != null) {
//            switch(usermy.getAccess()) {
//                case USER:
//                    map.put("catalog_exposition", expoDao.getAll());
//                    break;
//                case ADMIN:
//                    map.put("catalog_exposition", expoDao.getAll());
//                    break;
//            }
//        }
        return map;
    }

    public void setUserDao(UserDaoService userDao) {
        this.userDao = userDao;
    }

    public void setExpoDao(ExpositionDaoService expoDao) {
        this.expoDao = expoDao;
    }
}
