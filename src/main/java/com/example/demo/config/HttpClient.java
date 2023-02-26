package com.example.demo.config;

import com.example.demo.decorator.Key;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class HttpClient {
    public static Map<String,String> KeysMap = new HashMap<String,String>();

    public HttpClient(){
        Method[] declaredMethods = this.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            boolean annotationPresent = declaredMethod.isAnnotationPresent(Key.class);
            if(annotationPresent){
                Key annotation = declaredMethod.getAnnotation(Key.class);
                String value = annotation.value();
                String name = declaredMethod.getName();
                HttpClient.KeysMap.put(name,value);
            }
        }
    }

    @Key("-h 127.0.0.1 -p 10012")
    public String client(String url, LinkedHashMap<Object, Object> params){

        //  执行HTTP请求
        ResponseEntity<String> response = setKeys(url, params,"client");
        return response.getBody();
    }

    private ResponseEntity setKeys(String url,LinkedHashMap params,String methodName){
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String value = HttpClient.KeysMap.get(methodName);
        params.put("key",value);
        HttpEntity<LinkedHashMap<Object, Object>> requestEntity = new HttpEntity<LinkedHashMap<Object, Object>>(params, headers);
        ResponseEntity<String> response = client.exchange(url, HttpMethod.POST, requestEntity, String.class);

        return response;
    }

    public static void main(String[] args) {
        HttpClient httpClient = new HttpClient();
    }
}
