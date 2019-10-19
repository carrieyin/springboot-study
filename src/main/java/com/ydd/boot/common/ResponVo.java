package com.ydd.boot.common;

import lombok.Data;

@Data
public class ResponVo<T> {
    private boolean flag ;
    private String msg ;
    private T data ;

    public static <T> ResponVo success(T data){
        ResponVo vo = new ResponVo() ;
        vo.setFlag(true);
        vo.setData(data);
        return vo ;
    }

    public static ResponVo fail(String msg){
        ResponVo vo = new ResponVo() ;
        vo.setFlag(false);
        vo.setMsg(msg);
        return vo ;
    }
}
