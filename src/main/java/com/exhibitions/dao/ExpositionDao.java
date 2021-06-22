package com.exhibitions.dao;

import com.exhibitions.entity.Exposition;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ExpositionDao {

    //add exposition to exhibition
    void insertExposition(String name, Integer price, Date dataTime, Date period, String rooms);

    //del exposition
    boolean deleteExposition(Integer id);

    //find exposition by ID
    Optional<Exposition> findById(Integer id);

    //find exposition by Name
    List<Exposition> findByName(String name);

    //get all exposition
    List<Exposition> getAll();

    //sort buy price
    List<Exposition> getAllSort();

    //sort by price desk
    List<Exposition> getAllSortDesk();

    //get limited count rows exposition
    List<Exposition> getLimitRows(Integer firstRow, Integer rowCount);

    //get count all rows exposition in DB
    Integer getCountAllRows();



}
