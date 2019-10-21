package com.ydd.boot.controller;

import com.ydd.boot.common.ResponVo;
import com.ydd.boot.model.User;
import com.ydd.boot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/apis/UserController")
@Slf4j
public class IndexController {

    @Autowired
    private UserService userService ;


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
    private void deleteUserById(Integer id){
        for (int i = 0; i < userList.size(); i++) {
            User userinfo = userList.get(i);
            if(id == userinfo.getId()){
                userList.remove(i);
            }
        }
    }

    private void modUserById(Integer id, User user){
        for(User userinfo : userList){
            if(id == userinfo.getId()){
                userinfo.setName(user.getName());
                userinfo.setAddr(user.getAddr());
            }
        }
    }
    @RequestMapping(value = "/add")
    public ResponVo add(@RequestBody User user){
        user.setId(id++);
        userList.add(user);
        log.info("user:{}",userList) ;
        ResponVo vo = new ResponVo() ;
        vo.setFlag(true);
        vo.setData(userList);
        return vo ;
    }
    @RequestMapping(value = "/del")
    public ResponVo delete(@RequestParam Integer id){
        deleteUserById(id);
        log.info("user:{}",userList) ;
        return ResponVo.success(true);
    }
    @RequestMapping(value = "/mod")
    public ResponVo mod(@RequestBody User user){
        modUserById(user.getId(), user);
        return ResponVo.success(true);
    }

    @RequestMapping(value = "/select")
    public ResponVo select(@RequestBody User user){
        log.info("user:{}", user);
        List<User> list = filterUserByName(userList, user.getName());
        list = filterUserByAddr(list, user.getAddr());
        log.info("userList:{}", list);
        return ResponVo.success(list);
    }
}
