package com.ydd.boot.controller;

import com.ydd.boot.common.ResponVo;
import com.ydd.boot.model.User;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/apis/UserController")
@Slf4j
public class IndexController {
    private List<User> userList = new ArrayList();
    private Integer id = 0;
    private List findUser(User user){
        List<User> listUser = new ArrayList();
        for(User userinfo: userList){
            if(user.getName() == userinfo.getName() && user.getAddr() == userinfo.getAddr()){
                listUser.add(userinfo);
            }
        }
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
        List<User> list = findUser(user);
        log.info("user:{}", list);
        return ResponVo.success(list);
    }
}
