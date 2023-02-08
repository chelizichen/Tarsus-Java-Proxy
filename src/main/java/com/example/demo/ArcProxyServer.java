package com.example.demo;

import com.example.demo.decorator.ArcBaseServer;
import com.example.demo.decorator.ArcServerApplication;
import com.example.demo.register.Hello;
import com.example.demo.register.Test;

@ArcServerApplication(port = 9811)
public class ArcProxyServer extends ArcBaseServer {

    public static void main(String[] args) {
        ArcProxyServer c = new ArcProxyServer();
        final Hello hello = new Hello();
        final Test test = new Test();
        c.boost(ArcProxyServer.class);
    }

}
