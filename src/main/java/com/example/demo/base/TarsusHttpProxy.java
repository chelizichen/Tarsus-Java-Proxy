package com.example.demo.base;

import com.example.demo.config.HttpClient;
import com.example.demo.decorator.ProxyKey;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TarsusHttpProxy {
    public static Map<String,String> KeysMap = new HashMap<String,String>();

    public String createKey(String host,Integer port){
        return "-h "+host+" -p "+port;
    }

    public TarsusHttpProxy() {
        Method[] declaredMethods = this.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            boolean annotationPresent = declaredMethod.isAnnotationPresent(ProxyKey.class);
            if(annotationPresent){
                ProxyKey annotation = declaredMethod.getAnnotation(ProxyKey.class);
                String value = this.createKey(annotation.host(),annotation.port());
                String name = declaredMethod.getName();
                HttpClient.KeysMap.put(name,value);
            }
        }
    }

    public ResponseEntity proxyRequest(String url, LinkedHashMap params, String methodName){
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String value = HttpClient.KeysMap.get(methodName);
        params.put("key",value);
        HttpEntity<LinkedHashMap<Object, Object>> requestEntity = new HttpEntity<LinkedHashMap<Object, Object>>(params, headers);
        ResponseEntity<String> response = client.exchange(url, HttpMethod.POST, requestEntity, String.class);
        return response;
    }

}
