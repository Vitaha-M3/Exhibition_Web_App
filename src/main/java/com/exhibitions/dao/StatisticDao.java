package com.exhibitions.dao;

import java.util.List;
import java.util.Map;

public interface StatisticDao {

    //Returns an integer as the number of successful payments for all users
    Integer quantitySuccessPayed();

    //Returns an integer as the number of NO successful payments for all users
    Integer quantityNoSuccessPayed();

    //Return an long the sum of all successful payments
    Long sumAllSuccessPayed();

    //Returns a list of maps where each map is a string from the request in which the number of tickets purchased for the exhibition
    List<Map<String,Object>> purchasedTicketsOnExpo();

    //Returns statistics of visits of each user
    List<Map<String, Object>> userStatistic();
}
