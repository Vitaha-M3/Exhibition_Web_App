package com.exhibitions.controller;

import com.exhibitions.controller.command.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "MainServlet", value = "/Exhibition")
public class MainServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static final String COMMAND = "command";
    private static final String PAGE = "page";
    public static final String CHANGE_LANGUAGE = "changeLanguage";
    public static final String LOGIN = "login";
    private static final String MAIN = "main";
    private static final String LOGOUT = "logout";
    private static final String REGISTRATION = "registration";
    private Map<String, Command> commands;

    @Override
    public void init(){
        commands = new HashMap<>();
        commands.put(CHANGE_LANGUAGE ,new ChangeLanguageCommand());
        commands.put(LOGIN, new LoginCommand());
        commands.put(MAIN, new MainPageCommand());
        commands.put(LOGOUT, new LogOutCommand());
        commands.put(REGISTRATION, new RegistrationCommand());
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, NamingException {
        Command command = commands.get(request.getParameter(COMMAND));
        Map<String, Object> map = command.execute(request, response);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
        getServletContext().getRequestDispatcher((String) map.get(PAGE)).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            process(request, response);
        } catch (SQLException | NamingException throwables) {
            logger.log(Level.INFO,throwables);
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            process(request, response);
        } catch (SQLException | NamingException throwables) {
            logger.log(Level.INFO,throwables);
            throwables.printStackTrace();
        }
    }
}
