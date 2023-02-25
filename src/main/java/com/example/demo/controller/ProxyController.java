package com.example.demo.controller;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.HttpClient;
import com.example.demo.config.ProxyRequest;
import com.example.demo.utils.ret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;

@RequestMapping("proxy")
@RestController
public class ProxyController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HttpClient httpClient;

    @PostMapping("test")
    @ResponseBody
    public ret test() {
        ProxyRequest proxyRequest = new ProxyRequest(restTemplate);
        proxyRequest.setUrl("http://localhost:7099/proxy/interceptor");
        proxyRequest.put("name","tom");
        proxyRequest.put("age","20");
//        proxyRequest.put("value","10000");
        proxyRequest.setKey("127.0.0.1","10012");
        proxyRequest.setProxyParams("Hello","hello");
        String s = proxyRequest.sendProxyRequest();

        return ret.success(s);
    }

    @PostMapping("test_client")
    @ResponseBody
    public ret test1(){
        LinkedHashMap<Object, Object> body = new LinkedHashMap<>();
        body.put("key","-h 127.0.0.1 -p 10012");
        body.put("interFace","DemoInterFace");
        body.put("method","say");
        body.put("type","java");
        LinkedHashMap data = new LinkedHashMap();
        data.put("name","测试名称");
        data.put("age","21");
        body.put("data", data);
        String client = httpClient.client("http://localhost:7099/proxy/interceptor", HttpMethod.POST, body);
        return ret.success(client);
    }

    @RequestMapping("parmas_test")
    @ResponseBody
    public ret params_test(@RequestParam("key")String key,@RequestParam("value")String value){
        String data = key + value;
        return ret.success(data);
    }

    @PostMapping("post_params_test")
    public ret post_params_test(@RequestParam("key")String key,@RequestParam("value")String value){
        String data = key + value;
        return ret.success(data);
    }
}
