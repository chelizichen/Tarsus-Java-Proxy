package com.example.demo.base;

import com.example.demo.decorator.Proxy;
import com.example.demo.decorator.Target;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TarsusHttpProxy {

    public Map<String, String> KeysMap = new HashMap<String, String>();
    public String target;

    public TarsusHttpProxy() {
        // 构造函数 拿到所有子类的方法
        Method[] declaredMethods = this.getClass().getDeclaredMethods();

        final Target targetUrl = this.getClass().getAnnotation(Target.class);
        this.target = targetUrl.value();

        // 遍历子类
        for (Method declaredMethod : declaredMethods) {
            boolean annotationPresent = declaredMethod.isAnnotationPresent(Proxy.class);
            // 反射拿到对应注解的值
            if (annotationPresent) {
                Proxy annotation = declaredMethod.getAnnotation(Proxy.class);
                String name = declaredMethod.getName();
                KeysMap.put(name, annotation.value());
            }
        }
    }

    public ResponseEntity proxyRequest(LinkedHashMap params, String methodName) {
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 根据方法名称得到
        String value = this.KeysMap.get(methodName);
        params.put("proxy", value);

        HttpEntity<LinkedHashMap<Object, Object>> requestEntity = new HttpEntity<LinkedHashMap<Object, Object>>(params, headers);
        ResponseEntity<String> response = client.exchange(this.target, HttpMethod.POST, requestEntity, String.class);
        return response;
    }
}

//class TestA{
//    public void Say(){
//        final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
//        System.out.println(methodName);
//    }
//    public static void main(String[] args) {
//        new TestA().Say();
//        new TestB().Test1();
//        System.out.println("hello world");
//    }
//}
//
//class TestB{
//    public void Test1(){
//        final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
//        System.out.println(methodName);
//    }
//}
