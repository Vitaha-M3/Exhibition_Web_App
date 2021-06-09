package com.exhibitions.controller.command;

import com.exhibitions.controller.command.Command;
import com.exhibitions.service.RegistrationServiceDefault;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import java.util.Map;

public class RegistrationCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    RegistrationServiceDefault registerService = new RegistrationServiceDefault();
    public static final String PAGE = "page";
    public static final String INDEX_JSP = "/index.jsp";

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        RequestCustomer customer = RequestCustomer.fromRequestParameters(request);
//        customer.setAsRequestAttributes(request);
//        List violations = customer.validate();
//
//        if (!violations.isEmpty()) {
//            request.setAttribute("violations", violations);
//        }
//
//        String url = determineUrl(violations);
//        request.getRequestDispatcher(url).forward(request, response);
//    }

//    private String determineUrl(List violations) {
//        if (!violations.isEmpty()) {
//            return "/registration.jsp";
//        } else {
//            return "/helloUser.jsp";
//        }
//    }

    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException {
        Map<String, Object> map = new HashMap<>();
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        final Integer access_id = 2;
        boolean registrationYES = registerService.registration(login, email, password, name);
        if(registrationYES) {
            //map.put(PAGE, INDEX_JSP);

                request.setAttribute("login", login);
                request.setAttribute("name", name);
                request.setAttribute("email", email);
                request.setAttribute("accces", access_id);
            map.put(PAGE,"/helloUser.jsp");
        }
            else map.put(PAGE, "/registration.jsp");
        return map;
    }

//    private static class RequestCustomer {
//
//        private final String firstName;
//        private final String lastName;
//        private final String email;
//
//        private RequestCustomer(String firstName, String lastName, String email) {
//            this.firstName = firstName;
//            this.lastName = lastName;
//            this.email = email;
//        }
//
//        public static RequestCustomer fromRequestParameters(HttpServletRequest request) {
//            return new RequestCustomer(
//                    request.getParameter("firstname"),
//                    request.getParameter("lastname"),
//                    request.getParameter("email"));
//        }
//

//        public List<String> validate() {
//            List<String> violations = new ArrayList<>();
//            if (!ValidatorInput.validateString(firstName)) {
//                violations.add("Имя является обязательным полем");
//            }
//            if (!ValidatorInput.validateString(lastName)) {
//                violations.add("Фамилия является обязательным полем");
//            }
//            if (!ValidatorInput.validateMail(email)) {
//                violations.add("Email должен быть правильно сформирован");
//            }
//            return violations;
//        }
}

