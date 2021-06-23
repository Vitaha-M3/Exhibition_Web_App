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
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * The main servlet acts as a command controller,
 * processes requests depending on which command came from the user
 * @see javax.servlet.http.HttpServlet
 * @author Vitalii
 */

@WebServlet(name = "MainServlet", value = "/Exhibition")
public class MainServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static final String COMMAND = "command";
    private static final String PAGE = "page";
    private static final String CHANGE_LANGUAGE = "changeLanguage";
    private static final String LOGIN = "login";
    private static final String MAIN = "main";
    private static final String LOGOUT = "logout";
    private static final String REGISTRATION = "registration";
    private static final String GO_TO_REGISTRATION = "go_to_registration";
    private static final String USER_HOME = "user_service";
    private static final String BUY = "buyService";
    private static final String ADMIN = "adminService";
    private Map<String, Command> commands;

    @Override
    public void init(){
        commands = new HashMap<>();
        commands.put(CHANGE_LANGUAGE ,new ChangeLanguageCommand());
        commands.put(LOGIN, new LoginCommand());
        commands.put(MAIN, new MainPageCommand());
        commands.put(LOGOUT, new LogOutCommand());
        commands.put(REGISTRATION, new RegistrationCommand());
        commands.put(GO_TO_REGISTRATION, new GoToRegistrationCommand());
        commands.put(USER_HOME, new UserHomeCommand());
        commands.put(BUY, new BuyTicketsCommand());
        commands.put(ADMIN, new AdminCommand());
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, NamingException, ParseException {
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
        } catch (SQLException | NamingException | ParseException throwables) {
            logger.log(Level.INFO,throwables);
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            process(request, response);
        } catch (SQLException | NamingException | ParseException throwables) {
            logger.log(Level.INFO,throwables);
            throwables.printStackTrace();
        }
    }
}
