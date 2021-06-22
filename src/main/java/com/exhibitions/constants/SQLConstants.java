package com.exhibitions.constants;

public class SQLConstants {

    /** Script for User*/
    public static final String SELECT_USER_BY_LOGIN = "SELECT * FROM user WHERE login = ?;";
    public static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE id = ?;";
    public static final String INSERT_USER = "INSERT INTO user (login, email, password, name)" +
            "VALUES (?, ?, ?, ?);";
    public static final String UPDATE_USER = "UPDATE user SET login = ?, email = ?, password = ?, name = ?,  access_id = 2" +
            "WHERE id = ?;";
    public static final String GET_ALL_USERS = "SELECT * FROM user;";
//    public static final String FIND_MY_TICKETS = "SELECT orders.id, exposition.name, exposition.period, exposition.rooms, exposition.price " +
//            "FROM orders JOIN user ON orders.user_id = user.id JOIN exposition ON orders.exposition_id = exposition.id " +
//            "WHERE status_id=2 and period>now() and user.id = ?;";

    /** Script for Room */
    public static final String INSERT_ROOM = "INSERT INTO rooms (id_room, room) VALUES (?, ?);";
    public static final String DELETE_ROOM = "DELETE FROM rooms WHERE id_room = ?;";

    /** Script for Exposition*/
    public static final String INSERT_EXPOSITION = "INSERT INTO exposition (name, price, date_time, period, rooms)" +
            "VALUES (?, ?, ?, ?, ?);";
    public static final String DELETE_EXPOSITION = "DELETE FROM exposition WHERE id = ?;";
    public static final String SELECT_EXPOSITION_BY_ID = "SELECT * FROM exposition WHERE id = ?";
    public static final String SELECT_EXPOSITION_BY_NAME = "SELECT * FROM exposition WHERE name = ?";
    public static final String GET_ALL_EXPOSITION = "SELECT * FROM exposition;";
    public static final String GET_LIMIT_ROWS_EXPOSITION = "SELECT * FROM exposition LIMIT ?, ?;";
    public static final String GET_COUNT_ALL_ROW = "SELECT COUNT(*) FROM exposition;";
    //sort
    public static final String GET_ALL_EXPO_SORTED = "SELECT * FROM exposition ORDER BY price;";
    public static final String GET_ALL_EXPO_SORTED_DESK = "SELECT * FROM exposition ORDER BY price DESC;";

    /** Script for Orders*/
    public static final String GET_ALL_ORDERS = "SELECT * FROM orders;";
    public static final String INSERT_ORDER = "INSERT INTO orders (billing, user_id, status_id, exposition_id)" +
            "VALUE (?, ?, ?, ?);";
    public static final String UPDATE_ORDER = "UPDATE orders SET status_id = '2'" +
            "WHERE (id =  ?) and (status_id = ?) and (user_id = ?) and (exposition_id = ?);";
    public static final String FIND_ORDER_ID_BY_USER = "SELECT orders.id FROM orders WHERE (user_id = ?) and (status_id = ?) and (exposition_id = ?);";

    /** Script for Admin Statistic*/
    public static final String FIND_QUANTITY_SUCCESS_PAYED = "SELECT COUNT(*) AS 'col' FROM orders WHERE status_id =2;";
    public static final String FIND_QUANTITY_NOT_SUCCESS_PAYED = "SELECT COUNT(*) AS 'col' FROM orders WHERE status_id =1;";
    public static final String SUM_ALL_SUCCESS_PAYED = "SELECT  SUM(orders.billing) AS 'sum' FROM orders WHERE status_id=2;";
    public static final String SHOW_QUANTITY_PURCHASED_TICKETS_ON_EXPOSITION = "SELECT exposition.id AS 'Exposition ID', exposition.name, period, count(*) AS 'Buy Tickets', sum(billing) AS 'Sum billing for exposition' " +
            "FROM orders JOIN exposition ON orders.exposition_id = exposition.id WHERE status_id = 2 GROUP BY exposition_id;";
    public static final String SHOW_USERS_STATISTIC = "SELECT user_id, user.name, email, COUNT(*) AS 'purchasedTickets', GROUP_CONCAT(exposition_id) AS 'visitThisExposition', SUM(billing) AS 'sumBuy' " +
            "FROM orders JOIN user ON orders.user_id = user.id JOIN exposition ON orders.exposition_id = exposition.id " +
            "WHERE status_id = 2 GROUP BY user.id";

}
