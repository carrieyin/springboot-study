package com.ydd.boot.controller;

import com.ydd.boot.common.ResponVo;
import com.ydd.boot.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/apis/UserController")
@Slf4j
public class IndexController {
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
    private List findUser(User user){
        List listUser = new ArrayList<User>();
        for(User userinfo: userList){
            if((user.getName().equals(userinfo.getName())) && (user.getAddr().equals(userinfo.getAddr()))){
                listUser.add(userinfo);
            }
        }
        log.info("select---:{}", listUser);
        return listUser;
    }

    @RequestMapping(value = "/add")
    public ResponVo add(@RequestBody User user){
        user.setId(id++);
        userList.add(user);
        log.info("user:{}",userList) ;
        return ResponVo.success(userList);
    }

    @RequestMapping(value = "/delete")
    public ResponVo delete(@RequestBody User user){
        userList.add(user);
        log.info("user:{}",userList) ;
        return ResponVo.success(userList);
    }

    @RequestMapping(value = "/select")
    public ResponVo select(@RequestBody User user){
        log.info("user:{}", user);
        List<User> list;
        list = filterUserByName(userList, user.getName());
        list = filterUserByAddr(list, user.getAddr());
        log.info("userList:{}", list);
        return ResponVo.success(list);
    }
}
