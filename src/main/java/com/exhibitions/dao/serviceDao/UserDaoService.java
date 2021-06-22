package com.exhibitions.dao.serviceDao;

import com.exhibitions.dao.DBManager;
import com.exhibitions.dao.UserDao;
import com.exhibitions.entity.Access;
import com.exhibitions.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.exhibitions.constants.SQLConstants.*;

public class UserDaoService implements UserDao {
    private static final Logger logger = LogManager.getLogger(UserDaoService.class);

    private static final String ID = "id";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ACCESS = "access_id";
    private static final String EMAIL = "email";
    private static final String NAME = "name";

    @Override
    public Optional<User> findByLogin(String login) {
        Connection con = null;
        User user = null;
        try {
            con = DBManager.getConnection();
            PreparedStatement selectStatement = con.prepareStatement(SELECT_USER_BY_LOGIN);
            selectStatement.setString(1, login);
            user = initUser(selectStatement);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        } finally {
            DBManager.closeConnection(con);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findById(Integer userid) {
        Connection con = null;
        User user = null;
        try {
            con = DBManager.getConnection();
            PreparedStatement selectStatement = con.prepareStatement(SELECT_USER_BY_ID);
            selectStatement.setInt(1, userid);
            user = initUser(selectStatement);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            DBManager.closeConnection(con);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public void insertUser(String login, String email, String password, String name) {
        Connection con = null;
        try {
            con = DBManager.getConnection();
            con.setAutoCommit(false);
            PreparedStatement insertSt = con.prepareStatement(INSERT_USER);
            initInsertSt(insertSt, login, email, password, name);
            insertSt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            DBManager.rollback(con);
        } finally {
            DBManager.closeConnection(con);
        }
    }

    private void initInsertSt(PreparedStatement insSt, String login, String email, String password, String name) throws SQLException {
            insSt.setString(1, login);
            insSt.setString(2, email);
            insSt.setString(3, password);
            insSt.setString(4, name);
    }

    @Override
    public List<User> getAll() {
        Connection con = null;
        List<User> users = new ArrayList<>();
        try {
            con = DBManager.getConnection();
            Statement getAll = con.createStatement();
            ResultSet set = getAll.executeQuery(GET_ALL_USERS);
            initList(set, users);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            DBManager.closeConnection(con);
        }
        return users;
    }

    private void initList(ResultSet set, List<User> users) throws SQLException {
        while(set.next()) {
            User user = mySomeUser(set);;
            users.add(user);
        }
    }

    @Override
    public void update(String login, String email, String password, String name) {
        Connection con = null;
        try {
            con = DBManager.getConnection();
            con.setAutoCommit(false);
            PreparedStatement updateSt = con.prepareStatement(UPDATE_USER);
            initUpdateSt(updateSt, login, email, password, name);
            updateSt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            DBManager.rollback(con);
        } finally {
            DBManager.closeConnection(con);
        }
    }

    @Override
    public void remove(Integer userID) {}

    private User initUser(PreparedStatement st) throws SQLException {
        ResultSet set = st.executeQuery();
        if(set.next()) {
            return mySomeUser(set);
        }
        return null;
    }

    private void initUpdateSt(PreparedStatement updateSt, String login, String email, String password, String name) throws SQLException {
        updateSt.setString(1,login);
        updateSt.setString(2,email);
        updateSt.setString(3,password);
        updateSt.setString(4,name);
    }

    private User mySomeUser(ResultSet set) throws SQLException {
        User user = new User();
        user.setId(set.getInt(ID));
        user.setLogin(set.getString(LOGIN));
        user.setPassword(set.getString(PASSWORD));
        user.setEmail(set.getString(EMAIL));
        user.setName(set.getString(NAME));
        Integer access = set.getInt(ACCESS);
        switch (access){
            case 1: user.setAccess(Access.ADMIN);break;
            case 2: user.setAccess(Access.USER);break;
            case 3: user.setAccess(Access.GUEST);break;
        }
        return user ;
    }
}
