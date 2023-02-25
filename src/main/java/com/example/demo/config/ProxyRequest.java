package com.example.demo.config;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class ProxyRequest {
    public String interFace;
    public String method;
    public String key;
    public String url;
    public Map data;
    private RestTemplate restTemplate;

    public ProxyRequest(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
        this.data =  new HashMap();
    }
    public void setUrl(String url){
        this.url = url;
    }

    public String sendProxyRequest() throws NullPointerException{
        MultiValueMap requestEntity  = new LinkedMultiValueMap();

        requestEntity.add("type","java");
        requestEntity.add("interFace",this.interFace);
        requestEntity.add("method",this.method);
        requestEntity.add("key",this.key);
        requestEntity.add("data", this.data);

        System.out.println(this.url);
        System.out.println(String.valueOf(requestEntity));

        String s = restTemplate.postForObject(this.url, requestEntity, String.class);
        return s;

    }

    public void setProxyParams(String interFace,String method){
        this.interFace = interFace;
        this.method = method;
    }

    public void setData(HashMap data){
        this.data = data;
    }

    public void setKey(String host,String port){
        this.key = "-h "+host+" -p "+port;
    }

    public <T>void put(String key,T value){
        this.data.put(key,value);
    }

//        requestEntity.add("interFace","Hello");
//        requestEntity.add("method","hello");
//        requestEntity.add("key","-h 127.0.0.1 -p 10012");

}
