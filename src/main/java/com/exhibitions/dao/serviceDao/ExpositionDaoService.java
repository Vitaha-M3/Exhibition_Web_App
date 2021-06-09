package com.exhibitions.dao.serviceDao;

import com.exhibitions.dao.DBManager;
import com.exhibitions.dao.ExpositionDao;
import com.exhibitions.entity.Exposition;
import com.exhibitions.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.exhibitions.constants.SQLConstants.*;

public class ExpositionDaoService implements ExpositionDao {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void insertExposition(String name, Integer price, Date data_time, Date period, String rooms) {
        Connection con = null;
        try {
            con = DBManager.getConnection();
            con.setAutoCommit(false);
            PreparedStatement insertSt = con.prepareStatement(INSERT_EXPOSITION);
            initInsertExpoSt(insertSt, name, price, data_time, period, rooms);
            insertSt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            DBManager.rollback(con);
        } finally {
            DBManager.closeConnection(con);
        }
    }

    private void initInsertExpoSt(PreparedStatement insertSt, String name, Integer price, Date data_time, Date period, String rooms) throws SQLException {
        insertSt.setString(1, name);
        insertSt.setInt(2, price);
        insertSt.setDate(3, data_time);
        insertSt.setDate(4, period);
        insertSt.setString(5, rooms);
    }

    @Override
    public boolean deleteExposition(Integer id) {
        Connection con = null;
        int rowsUpdated = 0;
        try {
            con = DBManager.getConnection();
            PreparedStatement updateSt = con.prepareStatement(DELETE_EXPOSITION);
            rowsUpdated = updateSt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "ExpositionDAOService delete: ", e);
        } finally {
            DBManager.closeConnection(con);
        }
        return rowsUpdated == 1;
    }

    @Override
    public Optional<Exposition> findById(Integer id) {
        Connection con = null;
        Exposition expo = null;
        try {
            con = DBManager.getConnection();
            PreparedStatement selectStatement = con.prepareStatement(SELECT_EXPOSITION_BY_ID);
            selectStatement.setInt(1, id);
            expo = initExpo(selectStatement);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            DBManager.closeConnection(con);
        }
        return Optional.ofNullable(expo);
    }

    private Exposition initExpo(PreparedStatement selectStatement) throws SQLException {
        ResultSet set = selectStatement.executeQuery();
        Exposition expo = new Exposition();
        if(set.next()){
            expo.setId(set.getInt("id"));
            expo.setName(set.getString("name"));
            expo.setPrice(set.getInt("price"));
            expo.setData_time(set.getDate("date_time"));
            expo.setPeriod(set.getDate("period"));
            expo.setRooms(set.getString("rooms"));
        }
        return  expo;
    }

    @Override
    public Optional<Exposition> findByName(String name) {
        Connection con = null;
        Exposition expo = null;
        try {
            con = DBManager.getConnection();
            PreparedStatement selectStatement = con.prepareStatement(SELECT_EXPOSITION_BY_NAME);
            selectStatement.setString(1, name);
            expo = initExpo(selectStatement);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            DBManager.closeConnection(con);
        }
        return Optional.ofNullable(expo);
    }

    @Override
    public List<Exposition> getAll() {
        Connection con = null;
        List<Exposition> expositions = new ArrayList<>();
        try {
            con = DBManager.getConnection();
            Statement getAll = con.createStatement();
            ResultSet set = getAll.executeQuery(GET_ALL_EXPOSITION);
            initList(set, expositions);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            DBManager.closeConnection(con);
        }
        return expositions;
    }

    private void initList(ResultSet set, List<Exposition> expositions) throws SQLException {
        while (set.next()){
            Exposition expo = new Exposition();
            expo.setId(set.getInt("id"));
            expo.setName(set.getString("name"));
            expo.setPrice(set.getInt("price"));
            expo.setData_time(set.getDate("date_time"));
            expo.setPeriod(set.getDate("period"));
            expo.setRooms(set.getString("rooms"));
            expositions.add(expo);
        }
    }
}
