package com.exhibitions.controller.command;

import com.exhibitions.controller.validators.ValidatorInput;
import com.exhibitions.service.RegistrationServiceDefault;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

public class RegistrationCommand implements Command {
    RegistrationServiceDefault registerService = new RegistrationServiceDefault();
    public static final String PAGE = "page";
    public static final String INDEX_JSP = "/index.jsp";
    public static final String REGISTRATION_JSP = "/registration.jsp";

    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException {
        Map<String, Object> map = new HashMap<>();
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");

        List<String> violations = validate(login, email, name, password);
        boolean registrationYES = false;

        if(violations.isEmpty()){
            registrationYES = registerService.registration(login, email, password, name);
            if(!registrationYES){
                violations.add("This user already exists!");
            }
        }

        if (registrationYES) {
            request.setAttribute("name", name);
            map.put(PAGE, INDEX_JSP);
        } else{
            request.setAttribute("violations", violations);
            map.put(PAGE, REGISTRATION_JSP);
        }

        return map;
    }

    private List<String> validate(String login, String email, String name, String password) {
        List<String> violations = new ArrayList<>();
        if (!ValidatorInput.validateString(login)) {
            violations.add("The Login field is required!");
        }
        if (!ValidatorInput.validateString(name)) {
            violations.add("The Name field is required!");
        }
        if (!ValidatorInput.validateMail(email)) {
            violations.add("Email entered incorrectly!");
        }
        if (!ValidatorInput.validateString(password)) {
            violations.add("Password is required!");
        }
        return violations;
    }
}

