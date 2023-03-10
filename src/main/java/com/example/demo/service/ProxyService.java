package com.example.demo.service;
import com.alibaba.fastjson2.JSON;
import com.darylteo.rx.promises.java.Promise;
import com.example.demo.base.TarsusRequest;
import com.example.demo.dto.TestData;
import com.example.demo.proxy.RpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ProxyService {


    @Autowired
    RpcClient rpcClient;

    // Test Event-Driven Model

    public String TestEventDriven(){
        Promise<Map> stringPromise = new Promise();

        stringPromise.then(s -> {
            System.out.println("ASYNC 1 IS "+ s);
            Promise<String> stringPromise1 = new Promise<>();
//            String s2 = AsyncEvent2();
//            stringPromise1.fulfill(s2);
//            stringPromise.then(s1->{
//                System.out.println("ASYNC 2 IS "+ s);
//            });
        });

        Map s = AsyncEvent1();
        stringPromise.fulfill(s);

        return "1";

    }

    public Map AsyncEvent1(){
        LinkedHashMap<String, Object> body = new LinkedHashMap<>();
        body.put("interFace","DemoInterFace");
        body.put("method","say");

        LinkedHashMap data = new LinkedHashMap();
        data.put("name","测试名称");
        data.put("age","21");

        body.put("data", data);
        Map client = rpcClient.TarsusRpcRequest(body);
        return client;
    }

    public Map AsyncEvent2(TestData testData){

        LinkedHashMap<String, Object> payload = TarsusRequest.create("DemoInterFace", "hello")
                .assemble("name", testData.name)
                .assemble("age", testData.age)
                .payload();
        Map client = rpcClient.TarsusRpcRequest(payload);
        return client;
    }

}
