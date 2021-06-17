package com.exhibitions.service;

import java.util.List;
import java.util.Map;

public interface AdminStatisticService {

    Integer getSuccessPayed();

    Integer getNoSuccessPayed();

    Long getSumAllSuccessPayed();

    List<Map<String,Object>> getPurchasedTickOnExpo ();

    List<Map<String, Object>> getStatisticOnUsers();
}
