package com.exhibitions.service;

import com.exhibitions.dao.serviceDao.UserDaoService;
import com.exhibitions.entity.User;
import com.exhibitions.service.interfaceService.RegistrationService;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Optional;

public class RegistrationServiceDefault implements RegistrationService {
    private static final String MESSAGE = "message";
    private static final Object ERROR_MESSAGE = "User whith this LOGIN is present, come up with another login";
    private final UserDaoService userDao = new UserDaoService();
    
    @Override
    public boolean registration(String login, String email, String password, String name) throws SQLException, NamingException {
        Optional<User> user = userDao.findByLogin(login);
        if(user.isPresent())
            return false;
        else{
            userDao.insertUser(login, email, password, name);
            return true;
        }
    }
}
