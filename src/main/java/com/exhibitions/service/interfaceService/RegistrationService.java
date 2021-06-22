package com.exhibitions.service.interfaceService;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface RegistrationService {
    boolean registration(String login, String email, String password, String name)
            throws SQLException, NamingException;
}
