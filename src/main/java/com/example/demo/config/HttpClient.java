package com.example.demo.config;

import com.example.demo.base.TarsusHttpProxy;
import com.example.demo.decorator.ProxyKey;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
public class HttpClient extends TarsusHttpProxy {


    @ProxyKey(port = 10012,host = "127.0.0.1")
    public String client(String url, LinkedHashMap<Object, Object> params){
        //  执行HTTP请求
        ResponseEntity<String> response = proxyRequest(url, params,"client");
        return response.getBody();
    }

}
