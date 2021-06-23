package com.exhibitions.controller.command;

import com.exhibitions.controller.command.GoToRegistrationCommand;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Map;

public class GoToRegistrationCommandTest {
    @Mock
    private HttpServletRequest requestMock;
    @Mock
    private HttpServletResponse responseMock;

    GoToRegistrationCommand testingInstance = new GoToRegistrationCommand();

    @Test
    public void shouldReturnRegistrationPage() throws SQLException, NamingException {
        Map<String, Object> map = testingInstance.execute(requestMock, responseMock);
        String page = (String) map.get("page");
        Assertions.assertEquals("/registration.jsp", page);
    }
}
