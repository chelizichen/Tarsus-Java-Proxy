package com.example.demo.service;

import com.darylteo.rx.promises.java.Promise;
import com.darylteo.rx.promises.java.functions.PromiseAction;
import com.example.demo.config.ProxyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProxyService {
//    static String proxyUrl = "http://localhost:7099/proxy/interceptor";
//    static String host = "127.0.0.1";
//    static String port = "10012";
//
//    @Autowired
//    RestTemplate restTemplate;
//
//    // Test Event-Driven Model
//
//    public String TestEventDriven(){
//        Promise<String> stringPromise = new Promise<String>();
//
//        stringPromise.then(s -> {
//            System.out.println("ASYNC 1 IS "+ s);
//            Promise<String> stringPromise1 = new Promise<>();
//            String s2 = AsyncEvent2();
//            stringPromise1.fulfill(s2);
//            stringPromise.then(s1->{
//                System.out.println("ASYNC 2 IS "+ s);
//            });
//        });
//
//        String s = AsyncEvent1();
//        stringPromise.fulfill(s);
//
//        return "1";
//
//    }
//
//    public String AsyncEvent1(){
//        ProxyRequest proxyRequest = new ProxyRequest(restTemplate);
//        proxyRequest.setUrl(proxyUrl);
//        proxyRequest.put("name","tom");
//        proxyRequest.put("age","20");
//        proxyRequest.setKey(host,port);
//        proxyRequest.setProxyParams("DemoInterFace","say");
//        String s = proxyRequest.sendProxyRequest();
//        return s;
//    }
//
//    public String AsyncEvent2(){
//        ProxyRequest proxyRequest = new ProxyRequest(restTemplate);
//        proxyRequest.setUrl(proxyUrl);
//        proxyRequest.put("name","tom");
//        proxyRequest.put("age","20");
//        proxyRequest.setKey(host,port);
//        proxyRequest.setProxyParams("DemoInterFace","hello");
//        String s = proxyRequest.sendProxyRequest();
//        return s;
//    }

}
