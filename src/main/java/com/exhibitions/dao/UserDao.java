package com.exhibitions.dao;

import com.exhibitions.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    //find user by Login and by Id
    Optional<User> findByLogin(String login);
    Optional<User> findById(Integer userid);

    //insert user to table users
    void insertUser(String login, String email, String password, String name);

    //reed
    List<User> getAll();

    //update
    void update(String login, String email, String password, String name);

    //delete
    void remove(Integer userID);


}
