package com.exhibitions.dao.serviceDao;

import com.exhibitions.dao.DBManager;
import com.exhibitions.dao.RoomDao;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.exhibitions.constants.SQLConstants.DELETE_ROOM;
import static com.exhibitions.constants.SQLConstants.INSERT_ROOM;

public class RoomDaoService implements RoomDao {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void inserRoom(Integer id_room, String room) {
        Connection con = null;
        try {
            con = DBManager.getConnection();
            con.setAutoCommit(false);
            PreparedStatement insertSt = con.prepareStatement(INSERT_ROOM);
            initInsertSt(insertSt, id_room, room);
            insertSt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            DBManager.rollback(con);
        } finally {
            DBManager.closeConnection(con);
        }
    }

    private void initInsertSt(PreparedStatement insertSt, Integer id_room, String room) throws SQLException {
        insertSt.setInt(1,id_room);
        insertSt.setString(2,room);
    }

    @Override
    public boolean deleteRoom(Integer id_room) {
        Connection con = null;
        int rowsUpdated = 0;
        try {
            con = DBManager.getConnection();
            PreparedStatement updateSt = con.prepareStatement(DELETE_ROOM);
            rowsUpdated = updateSt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "RoomDAOService delete: ", e);
        } finally {
            DBManager.closeConnection(con);
        }
        return rowsUpdated == 1;
    }
}
