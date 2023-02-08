package com.example.demo.register;

import com.example.demo.decorator.ArcInterFace;
import com.example.demo.decorator.ProxyInterFace;
import com.example.demo.utils.ret;

@ProxyInterFace(interFace = "TestInterFace")
public class Test extends ArcInterFace {
    public ret run(String[] args){
        return ret.success("测试111");
    }
}
