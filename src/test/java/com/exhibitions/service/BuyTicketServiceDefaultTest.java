package com.exhibitions.service;

import com.exhibitions.dao.serviceDao.ExpositionDaoService;
import com.exhibitions.dao.serviceDao.OrderDaoService;
import com.exhibitions.entity.Exposition;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class BuyTicketServiceDefaultTest {
    @Mock
    ExpositionDaoService expoDaoService;
    @Mock
    OrderDaoService orderDaoService;
    @Mock
    Exposition expoMock;
    @InjectMocks
    BuyTicketServiceDefault buyTicketServiceDefault;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindExpoByID() throws Exception {
        when(expoDaoService.findById(5)).thenReturn(Optional.of(expoMock));
        Optional<Exposition> result = buyTicketServiceDefault.findExpoByID("5");
        Assert.assertEquals(Optional.of(expoMock), result);
    }

    @Test
    public void testBillingByTicket() throws Exception {
        buyTicketServiceDefault.billingByTicket(Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0));
    }

    @Test
    public void testBuyTicketFinal() throws Exception {
        when(orderDaoService.updateOrder(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(true);
        when(orderDaoService.findOrderIdByUserId(anyInt(), anyInt(), anyInt())).thenReturn(Integer.valueOf(0));

        boolean result = buyTicketServiceDefault.buyTicketFinal(Integer.valueOf(0), Integer.valueOf(0));
        Assert.assertEquals(true, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme