package com.ydd.boot.service.impl;

import com.ydd.boot.model.User;
import com.ydd.boot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private List<User> userList = new ArrayList<>();
    private Integer id = 0;
    private List<User> filterUserByName(List<User> userList, String name){
        if("".equals(name)){
            return userList;
        }
        List<User> listUser = new ArrayList<>();
        for(User userinfo: userList){
            if(userinfo.getName().contains(name)){
                listUser.add(userinfo);
            }
        }
        return listUser;
    }
    private List<User> filterUserByAddr(List<User> userList, String addr){
        if("".equals(addr)){
            return userList;
        }
        List<User> listUser = new ArrayList<>();
        for(User userinfo: userList){
            if(userinfo.getAddr().contains(addr)){
                listUser.add(userinfo);
            }
        }
        return listUser;
    }
    public void deleteUserById(Integer id){
        for (int i = 0; i < userList.size(); i++) {
            User userinfo = userList.get(i);
            if(id.equals(userinfo.getId())){
                userList.remove(i);
            }
        }
    }

    @Override
    public void modUserById(Integer id, User user) {
        for(User userinfo : userList){
            if(id == userinfo.getId()){
                userinfo.setName(user.getName());
                userinfo.setAddr(user.getAddr());
            }
        }
    }

    @Override
    public List<User> select(User user) {
        List<User> list = filterUserByName(userList, user.getName());
        list = filterUserByAddr(list, user.getAddr());
        return  list;
    }

    @Override
    public List<User> addUser(User user) {
        user.setId(id++);
        userList.add(user);
        return userList;
    }
}
