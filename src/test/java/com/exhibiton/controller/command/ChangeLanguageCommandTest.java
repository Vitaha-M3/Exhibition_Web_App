package com.exhibiton.controller.command;

import com.exhibitions.controller.command.ChangeLanguageCommand;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Map;

public class ChangeLanguageCommandTest {
    @Mock
    private HttpServletRequest requestMock;
    @Mock
    private HttpServletResponse responseMock;
    @Mock
    private HttpSession sessionMock;

    private ChangeLanguageCommand testingInstance = new ChangeLanguageCommand();

    @Test
    public void shouldReturnMainPage() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(requestMock.getParameter("language")).thenReturn("en");
        Mockito.when(requestMock.getSession()).thenReturn(sessionMock);
        Map<String, Object> map = testingInstance.execute(requestMock, responseMock);
        String page = (String) map.get("page");
        Assert.assertEquals("/index.jsp", page);
    }
}
