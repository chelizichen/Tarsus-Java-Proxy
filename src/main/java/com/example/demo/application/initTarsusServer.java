package com.example.demo.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

@Component
@Configuration
public class initTarsusServer implements ApplicationRunner {

    @Value("${tarsus.name}")
    public String ProjectName;

    @Value("${tarsus.init_url}")
    public String init_url;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LinkedHashMap<Object, Object> body = new LinkedHashMap<>();
        body.put("servantName",ProjectName);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity linkedHashMapHttpEntity = new HttpEntity<>(body,headers);
        restTemplate.exchange(init_url, HttpMethod.POST,linkedHashMapHttpEntity,String.class);
    }
}
