package com.example.demo.service;

import com.darylteo.rx.promises.java.Promise;
import com.darylteo.rx.promises.java.functions.PromiseAction;
import com.example.demo.config.HttpClient;
import com.example.demo.config.ProxyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

@Service
public class ProxyService {


    @Autowired
    HttpClient httpClient;

    // Test Event-Driven Model

    public String TestEventDriven(){
        Promise<String> stringPromise = new Promise<String>();

        stringPromise.then(s -> {
            System.out.println("ASYNC 1 IS "+ s);
            Promise<String> stringPromise1 = new Promise<>();
            String s2 = AsyncEvent2();
            stringPromise1.fulfill(s2);
            stringPromise.then(s1->{
                System.out.println("ASYNC 2 IS "+ s);
            });
        });

        String s = AsyncEvent1();
        stringPromise.fulfill(s);

        return "1";

    }

    public String AsyncEvent1(){
        LinkedHashMap<Object, Object> body = new LinkedHashMap<>();
        body.put("interFace","DemoInterFace");
        body.put("method","say");
        body.put("type","java");
        LinkedHashMap data = new LinkedHashMap();
        data.put("name","测试名称");
        data.put("age","21");
        body.put("data", data);
        String client = httpClient.client("http://localhost:7099/proxy/interceptor", body);
        return client;
    }

    public String AsyncEvent2(){
        LinkedHashMap<Object, Object> body = new LinkedHashMap<>();
        body.put("interFace","DemoInterFace");
        body.put("method","hello");
        body.put("type","java");
        LinkedHashMap data = new LinkedHashMap();
        data.put("name","测试名称");
        data.put("age","21");
        body.put("data", data);
        String client = httpClient.client("http://localhost:7099/proxy/interceptor", body);
        return client;
    }

}
