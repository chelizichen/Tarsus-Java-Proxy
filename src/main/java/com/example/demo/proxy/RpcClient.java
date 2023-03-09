package com.example.demo.proxy;

import com.example.demo.base.TarsusHttpProxy;
import com.example.demo.decorator.Proxy;
import com.example.demo.decorator.Target;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
@Target("http://127.0.0.1:6011/gateway/tarsusRpc")
public class RpcClient extends TarsusHttpProxy {

    // Proxy 代表Http微服务项目
    @Proxy("DemoJavaProject")
    public String TarsusRpcServer(LinkedHashMap<Object, Object> params){
        //  请求RPC微服务的
        ResponseEntity<String> response = proxyRequest(params,"TarsusRpcServer");
        return response.getBody();
    }
}
