package com.example.demo.utils;


public class ret<T> {
    public Integer code;
    public String msg;
    public T data;

    ret(T data){
        this.code = 0;
        this.msg = "ok";
        this.data = data;
    }
    public static <T>ret success(T data){
        return new ret(data);
    }

}
