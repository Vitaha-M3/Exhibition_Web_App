package com.exhibitions.constants;

public class SQLConstants {

    public static final String SELECT_USER_BY_LOGIN = "SELECT * FROM user WHERE login = ?;";
    public static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE id = ?;";
    public static final String INSERT_USER = "INSERT INTO user (login, email, password, name)" +
            "VALUES (?, ?, ?, ?);";
    public static final String UPDATE_USER = "UPDATE user SET login = ?, email = ?, password = ?, name = ?,  access_id = 2" +
            "WHERE id = ?;";
    public static final String GET_ALL_USERS = "SELECT * FROM user;";
    public static final String INSERT_ROOM = "INSERT INTO rooms (id_room, room) VALUES (?, ?);";
    public static final String DELETE_ROOM = "DELETE FROM rooms WHERE id_room = ?;";
    public static final String INSERT_EXPOSITION = "INSERT INTO exposition (name, price, date_time, period, rooms)" +
            "VALUES (?, ?, ?, ?, ?);";
    public static final String DELETE_EXPOSITION = "DELETE FROM exposition WHERE id = ?;";
    public static final String SELECT_EXPOSITION_BY_ID = "SELECT * FROM exposition WHERE id = ?";
    public static final String SELECT_EXPOSITION_BY_NAME = "SELECT * FROM exposition WHERE name = ?";
    public static final String GET_ALL_EXPOSITION = "SELECT * FROM exposition;";
    public static final String GET_ALL_ORDERS = "SELECT * FROM orders;";
}
