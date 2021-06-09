package com.exhibitions.dao;

import com.exhibitions.entity.Exposition;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ExpositionDao {

    //add exposition to exhibition
    void insertExposition(String name, Integer price, Date data_time, Date period, String rooms);

    //del exposition
    boolean deleteExposition(Integer id);

    //find exposition by ID
    Optional<Exposition> findById(Integer id);

    //find exposition by Name
    Optional<Exposition> findByName(String name);

    //get all exposition
    List<Exposition> getAll();

}
