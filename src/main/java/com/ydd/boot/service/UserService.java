package com.ydd.boot.service;

import com.ydd.boot.model.User;

import java.util.List;

public interface UserService {
     List<User> addUser(User user) ;
     void deleteUserById(Integer id);
     void modUserById(Integer id, User user);
     List<User> select(User user);

}
