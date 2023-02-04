package com.example.demo.controller;
import com.alibaba.fastjson.JSON;
import com.example.demo.config.ProxyRequest;
import com.example.demo.utils.ret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@RequestMapping("proxy")
@RestController
public class ProxyController {

    @Autowired
    RestTemplate restTemplate;

    @PostMapping("test")
    @ResponseBody
    public ret test() {

        ProxyRequest proxyRequest = new ProxyRequest(restTemplate);
        proxyRequest.setUrl("http://localhost:5005/api/proxy/interceptor");
        proxyRequest.put("name","tom");
        proxyRequest.put("age","20");
        proxyRequest.put("value","10000");
        proxyRequest.setKey("127.0.0.1","10012");
        proxyRequest.setProxyParams("Hello","hello");
        String s = proxyRequest.sendProxyRequest();

        return ret.success(s);
    }
}
