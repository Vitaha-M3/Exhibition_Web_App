package com.exhibitions.service;

import com.exhibitions.dao.serviceDao.StatisticDaoService;
import com.exhibitions.service.interfaceService.AdminStatisticService;

import java.util.List;
import java.util.Map;

public class AdminStatisticServiceDefault implements AdminStatisticService {
    StatisticDaoService statisticDaoService = new StatisticDaoService();

    @Override
    public Integer getSuccessPayed(){
        return statisticDaoService.quantitySuccessPayed();
    }

    @Override
    public Integer getNoSuccessPayed(){
        return statisticDaoService.quantityNoSuccessPayed();
    }

    @Override
    public Long getSumAllSuccessPayed(){
        return statisticDaoService.sumAllSuccessPayed();
    }

    @Override
    public List<Map<String,Object>> getPurchasedTickOnExpo (){
        return statisticDaoService.purchasedTicketsOnExpo();
    }

    @Override
    public List<Map<String, Object>> getStatisticOnUsers(){
        return statisticDaoService.userStatistic();
    }
}
