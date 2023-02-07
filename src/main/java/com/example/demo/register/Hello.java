package com.example.demo.register;

import com.alibaba.fastjson.JSON;
import com.example.demo.decorator.ArcInterFace;
import com.example.demo.decorator.ProxyInterFace;
import com.example.demo.utils.ret;

import java.util.HashMap;

@ProxyInterFace(interFace = "HelloInterFace")
public class Hello extends ArcInterFace {

    public ret hello(String[] args){
        System.out.println(args);
        for (String s:args){
            System.out.println(s);
        }
        ret success = ret.success("123123123");
        System.out.println(success.toString());
        return success;
    }

    public ret say(String[] args){

        HashMap<String, String> hmp = new HashMap<>();
        hmp.put("a","1");
        hmp.put("b","2");
        hmp.put("c","3");
        ret success = ret.success(hmp);
        return success;
    }
}
