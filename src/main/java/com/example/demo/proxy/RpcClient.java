package com.example.demo.proxy;

import com.example.demo.base.TarsusHttpProxy;
import com.example.demo.decorator.Proxy;
import com.example.demo.decorator.Target;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * Proxy注解代表着服务群中的具体微服务
 * Target注解代表着远端微服务的具体地址
 * 在上线初期只需要修改Target的地址
 * 一般后台微服务都是使用的RPC协议调用
 * 但实际上也存在 Http 的微服务，在调用外网的地址时需要
 */

@Service
@Target("http://127.0.0.1:6011/gateway/tarususRpc")
public class RpcClient extends TarsusHttpProxy {

    // Proxy 代表Http微服务项目
    @Proxy("JavaDemo")
    public String TarsusRpcServer(LinkedHashMap<Object, Object> params){
        //  请求RPC微服务的
        ResponseEntity<String> response = proxyRequest(params,"TarsusRpcServer");
        return response.getBody();
    }
}
