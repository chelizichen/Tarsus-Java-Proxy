package com.example.demo.proxy;

import com.example.demo.base.TarsusHttpProxy;
import com.example.demo.decorator.Project;
import com.example.demo.decorator.Target;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
@Target("http://127.0.0.1:6011/gateway/tarsusHttp") // 微服务网关层
public class HttpClient extends TarsusHttpProxy {

    // Proxy 代表Http微服务项目
    @Project("DemoJavaProject")
    public String TarsusHttpServer(LinkedHashMap<Object, Object> params){
        //  请求RPC微服务的
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        ResponseEntity<String> response = proxyRequest(params,methodName);
        return response.getBody();
    }

}
