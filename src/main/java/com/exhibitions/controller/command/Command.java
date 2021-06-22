package com.exhibitions.controller.command;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;

public interface Command {
    Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException, IOException, ParseException;
}
