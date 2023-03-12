package com.example.demo.proxy;

import com.alibaba.fastjson2.JSON;
import com.example.demo.base.TarsusHttpProxy;
import com.example.demo.decorator.Project;
import com.example.demo.decorator.Target;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Proxy注解代表着服务群中的具体微服务
 * Target注解代表着远端微服务的具体地址
 * 在上线初期只需要修改Target的地址
 * 一般后台微服务都是使用的RPC协议调用
 * 但实际上也存在 Http 的微服务，在调用外网的地址时需要
 */

@Service
@Target("http://127.0.0.1:9811/gateway/tarsusRpc")
public class RpcClient extends TarsusHttpProxy {

    // Proxy 代表Http微服务项目
    @Project("NodeDemo")
    public Map TarsusRpcRequest(LinkedHashMap<String, Object> params){
        //  请求RPC微服务的
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        ResponseEntity<String> response = proxyRequest(params,methodName);
        final String body = response.getBody();
        final Map parse = (Map) JSON.parse(body);
        return parse;
    }

    @Project("JavaDemo")
    public Map TarsusJavaRpcRequest(LinkedHashMap<String, Object> params){
        //  请求RPC微服务的
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        ResponseEntity<String> response = proxyRequest(params,methodName);
        final String body = response.getBody();
        final Map parse = (Map) JSON.parse(body);
        return parse;
    }

}
