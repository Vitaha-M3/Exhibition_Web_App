package com.exhibitions.service;

import com.exhibitions.dao.serviceDao.StatisticDaoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

class AdminStatisticServiceDefaultTest {
    @Mock
    StatisticDaoService statisticDaoService;
    @InjectMocks
    AdminStatisticServiceDefault adminStatisticServiceDefault;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetSuccessPayed() {
        when(statisticDaoService.quantitySuccessPayed()).thenReturn(Integer.valueOf(0));

        Integer result = adminStatisticServiceDefault.getSuccessPayed();
        Assertions.assertEquals(Integer.valueOf(0), result);
    }

    @Test
    void testGetNoSuccessPayed() {
        when(statisticDaoService.quantityNoSuccessPayed()).thenReturn(Integer.valueOf(0));

        Integer result = adminStatisticServiceDefault.getNoSuccessPayed();
        Assertions.assertEquals(Integer.valueOf(0), result);
    }

    @Test
    void testGetSumAllSuccessPayed() {
        when(statisticDaoService.sumAllSuccessPayed()).thenReturn(Long.valueOf(1));

        Long result = adminStatisticServiceDefault.getSumAllSuccessPayed();
        Assertions.assertEquals(Long.valueOf(1), result);
    }

    @Test
    void testGetPurchasedTickOnExpo() {
        when(statisticDaoService.purchasedTicketsOnExpo()).thenReturn(Arrays.<Map<String, Object>>asList(new HashMap<String, Object>() {{
            put("String", "Map");
        }}));

        List<Map<String, Object>> result = adminStatisticServiceDefault.getPurchasedTickOnExpo();
        Assertions.assertEquals(Arrays.<Map<String, Object>>asList(new HashMap<String, Object>() {{
            put("String", "Map");
        }}), result);
    }

    @Test
    void testGetStatisticOnUsers() {
        when(statisticDaoService.userStatistic()).thenReturn(Arrays.<Map<String, Object>>asList(new HashMap<String, Object>() {{
            put("String", "Map");
        }}));

        List<Map<String, Object>> result = adminStatisticServiceDefault.getStatisticOnUsers();
        Assertions.assertEquals(Arrays.<Map<String, Object>>asList(new HashMap<String, Object>() {{
            put("String", "Map");
        }}), result);
    }
}
