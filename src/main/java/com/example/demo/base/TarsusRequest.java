package com.example.demo.base;

import java.util.LinkedHashMap;

public class TarsusRequest {
    // 微服务调用
    private LinkedHashMap<Object,Object> Body;
    private LinkedHashMap<String, Object> Data;
    public  static TarsusRequest create(String interFace,String method){
        return new TarsusRequest(interFace,method);
    }

    public TarsusRequest(String interFace, String method) {
        Body.put("interFace",interFace);
        Body.put("method",interFace);
    }

    public TarsusRequest assemble(String key,Object value){
        this.Data.put(key,value);
        return this;
    }

    public LinkedHashMap<Object,Object> payload(){
        Body.put("data",this.Data);
        return this.Body;
    }
}
