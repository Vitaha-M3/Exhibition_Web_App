package com.exhibitions.controller.command;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Map;

public interface Command {
    Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException;
}
