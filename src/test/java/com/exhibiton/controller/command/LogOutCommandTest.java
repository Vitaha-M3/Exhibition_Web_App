package com.exhibiton.controller.command;

import com.exhibitions.controller.command.LogOutCommand;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Map;

public class LogOutCommandTest {
    @Mock
    private HttpServletRequest requestMock;
    @Mock
    private HttpServletResponse responseMock;
    @Mock
    private HttpSession sessionMock;

    private LogOutCommand testingInstance = new LogOutCommand();

    @Test
    public void shouldReturnIndexPage() throws SQLException, NamingException {
        MockitoAnnotations.initMocks(this);
        Mockito.when(requestMock.getSession()).thenReturn(sessionMock);
        Map<String, Object> map = testingInstance.execute(requestMock, responseMock);
        String page = (String) map.get("page");
        Assertions.assertEquals("/index.jsp", page);
    }
}
