package com.exhibitions.dao.serviceDao;

import com.exhibitions.dao.DBManager;
import com.exhibitions.dao.ExpositionDao;
import com.exhibitions.entity.Exposition;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.exhibitions.constants.SQLConstants.*;

public class ExpositionDaoService implements ExpositionDao {
    private static final Logger logger = LogManager.getLogger(ExpositionDaoService.class);

    @Override
    public void insertExposition(String name, Integer price, java.util.Date dataTime, java.util.Date period, String rooms) {
        Connection con = null;
        try {
            con = DBManager.getConnection();
            con.setAutoCommit(false);
            PreparedStatement insertSt = con.prepareStatement(INSERT_EXPOSITION);
            initInsertExpoSt(insertSt, name, price, dataTime, period, rooms);
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
        insertSt.setDate(3,  new java.sql.Date(data_time.getTime()));
        insertSt.setDate(4, new java.sql.Date(period.getTime()));
        insertSt.setString(5, rooms);
    }

    @Override
    public boolean deleteExposition(Integer id) {
        Connection con = null;
        int rowsUpdated = 0;
        try {
            con = DBManager.getConnection();
            PreparedStatement updateSt = con.prepareStatement(DELETE_EXPOSITION);
            updateSt.setInt(1, id);
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
            expo.setDate(set.getDate("date_time"));
            expo.setPeriod(set.getDate("period"));
            expo.setRooms(set.getString("rooms"));
        }
        return  expo;
    }

    @Override
    public List<Exposition> findByName(String name) {
        Connection con = null;
        List<Exposition> expoByName = new ArrayList<>();
        try {
            con = DBManager.getConnection();
            PreparedStatement getAll =  con.prepareStatement(SELECT_EXPOSITION_BY_NAME);
            getAll.setString(1, name);
            ResultSet set = getAll.executeQuery();
            initList(set,expoByName);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            DBManager.closeConnection(con);
        }
        return expoByName;
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

    @Override
    public List<Exposition> getAllSort(){
        Connection connect = null;
        List<Exposition> expoSorted = new ArrayList<>();
        try {
            connect = DBManager.getConnection();
            Statement getAll = connect.createStatement();
            ResultSet set = getAll.executeQuery(GET_ALL_EXPO_SORTED);
            initList(set, expoSorted);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            DBManager.closeConnection(connect);
        }
        return expoSorted;
    }

    @Override
    public List<Exposition> getAllSortDesk(){
        Connection con = null;
        List<Exposition> expositions = new ArrayList<>();
        try {
            con = DBManager.getConnection();
            Statement getAllSortDesk = con.createStatement();
            ResultSet set = getAllSortDesk.executeQuery(GET_ALL_EXPO_SORTED_DESK);
            initList(set, expositions);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            DBManager.closeConnection(con);
        }
        return expositions;
    }

    @Override
    public List<Exposition> getLimitRows(Integer firstRow, Integer rowCount){
        Connection connection = null;
        List<Exposition> expoLimit = new ArrayList<>();
        try {
            connection = DBManager.getConnection();
            PreparedStatement getLimitRows =  connection.prepareStatement(GET_LIMIT_ROWS_EXPOSITION);
            getLimitRows.setInt(1, firstRow);
            getLimitRows.setInt(2,rowCount);
            ResultSet set = getLimitRows.executeQuery();
            initList(set,expoLimit);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            DBManager.closeConnection(connection);
        }
        return expoLimit;
    }

    @Override
    public Integer getCountAllRows(){
        Connection con = null;
        Integer expoCount = null;
        try {
            con = DBManager.getConnection();
            PreparedStatement getCountRows =  con.prepareStatement(GET_COUNT_ALL_ROW);
            ResultSet set = getCountRows.executeQuery();
            if(set.next()){
                expoCount = set.getInt(1);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            DBManager.closeConnection(con);
        }
        return expoCount;
    }

    private void initList(ResultSet set, List<Exposition> expositions) throws SQLException {
        while (set.next()){
            Exposition expo = new Exposition();
            expo.setId(set.getInt("id"));
            expo.setName(set.getString("name"));
            expo.setPrice(set.getInt("price"));
            expo.setDate(set.getDate("date_time"));
            expo.setPeriod(set.getDate("period"));
            expo.setRooms(set.getString("rooms"));
            expositions.add(expo);
        }
    }
}
