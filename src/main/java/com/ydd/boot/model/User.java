package com.ydd.boot.model;

import lombok.*;

@ToString
@Data
public class User{
    private Integer id;
    private String name ;
    private String addr ;



//    public static void main(String[] args) {
//        String name  ="package com.ydd.boot.model.User" ;
//        try {
//            User u = null ;
//            Class<?> aClass = Class.forName(name);
//            u = aClass.newInstance() ;
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
}