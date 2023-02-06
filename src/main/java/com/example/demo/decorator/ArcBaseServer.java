package com.example.demo.decorator;

import org.apache.tomcat.jni.Error;

import java.io.*;
import java.lang.annotation.AnnotationTypeMismatchException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ArcBaseServer {

    static String[] proto = new String[]{"[#1]",
            "[#2]", "[#3]", "[#4]", "[#5]",
            "[#6]", "[#7]", "[#8]", "[#9]",
            "[##]"
    };

    public void boost(Class SonClass){
        boolean hasAnnotation = SonClass.isAnnotationPresent(TestClassDecorator.class);
        if ( hasAnnotation ) {
            TestClassDecorator testAnnotation = (TestClassDecorator) SonClass.getAnnotation(TestClassDecorator.class);

            // 拿到 Port 和 Host
            Integer PORT = testAnnotation.port();
            this.createServer(PORT);
        }else {
            return;
        }
    }

    private void createServer(Integer port){
        try {
            ServerSocket serverSocket = new ServerSocket(port,20);

            InputStreamReader inSR = null;
            OutputStreamWriter outSW = null;

            while (true){
                // 使用ServerSocket对象中的方法accept，获取到请求的客户端对象Socket。
                Socket socket = serverSocket.accept();

                inSR = new InputStreamReader(socket.getInputStream(), "UTF-8");
                BufferedReader br = new BufferedReader(inSR);

                StringBuffer stf = new StringBuffer();

                String str = "";
                while ((str = br.readLine()) != null){
                    System.out.println("收到客户端消息2:" + str);
                    str = str.trim();
                    stf.append(str);

                    // 执行 结束 语句 并且 拆分相关字节流
                    if(str.endsWith("[#ENDL#]")){

                        String s0 = this.unpkgHead(0, stf);
                        String s1 = this.unpkgHead(1, stf);
                        String s2 = this.unpkgHead(2, stf);
                        System.out.println("消息全部发送完毕"+stf);
                        System.out.println("截取参数");
                        System.out.println(s0);
                        System.out.println(s1);
                        System.out.println(s2);

                        break;
                    }
                }

                outSW = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
                BufferedWriter bw = new BufferedWriter(outSW);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String unpkgHead(int start,StringBuffer data){
        int start_index = data.indexOf(ArcBaseServer.proto[start]);
        int start_next = data.indexOf(ArcBaseServer.proto[start + 1]);
        String head = data.substring(start_index + proto[start].length(), start_next);
        return head;
    }


    private String unpkgHead(int start,StringBuffer data,boolean isEnd){
        int start_index = data.indexOf(ArcBaseServer.proto[start]);
        int start_next;
        if(isEnd){
            start_next = data.indexOf(ArcBaseServer.proto[proto.length - 1]);
        }else{
            start_next = data.indexOf(ArcBaseServer.proto[start + 1]);
        }
        String head = data.substring(start_index + proto[start].length(), start_next);
        return head;
    }

    private void __read_rpc__(){

    }


}
