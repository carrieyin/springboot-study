package com.ydd.boot.model;

import lombok.*;

@ToString
@Data
public class User{
    private Integer id;
    private String name ;
    private String addr ;
    private String birthday;
    private String sex;
    private String hobby;

}