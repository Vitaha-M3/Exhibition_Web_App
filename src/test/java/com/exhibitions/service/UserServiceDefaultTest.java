package com.exhibitions.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.exhibitions.dao.serviceDao.ExpositionDaoService;
import com.exhibitions.entity.Exposition;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class UserServiceDefaultTest {
    @InjectMocks
    UserServiceDefault userServiceDefault = new UserServiceDefault();
    @InjectMocks
    ArrayList<Exposition> expositionList = new ArrayList<Exposition>();

    @Test
    public void testSortByPrice() {
        List<Exposition> actualSortByPriceResult = userServiceDefault.sortByPrice(expositionList);
        assertSame(expositionList, actualSortByPriceResult);
        assertEquals(0, actualSortByPriceResult.size());
        assertEquals(1L, userServiceDefault.getCountSort());
    }

    @Test
    public void testSortByPrice2() {
        userServiceDefault.setCountSort(3L);
        ArrayList<Exposition> expositionList = new ArrayList<Exposition>();
        List<Exposition> actualSortByPriceResult = userServiceDefault.sortByPrice(expositionList);
        assertSame(expositionList, actualSortByPriceResult);
        assertEquals(0, actualSortByPriceResult.size());
        assertEquals(4L, userServiceDefault.getCountSort());
    }

}

