package com.example.demo.controller;

import com.example.demo.dto.TestData;
import com.example.demo.service.ProxyService;
import com.example.demo.utils.ret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("proxy")
@RestController
public class ProxyController {

    @Autowired
    ProxyService proxyService;

    @PostMapping("test_client")
    @ResponseBody
    public ret test1() {
        String s = proxyService.TestEventDriven();
        return ret.success(s);
    }

    @PostMapping("linked_test")
    @ResponseBody
    public ret LinkedReuqest(@RequestBody TestData testData){
        System.out.println("testData"+testData.name);
        System.out.println("testData"+testData.age);
        Map s = proxyService.AsyncEvent2(testData);
        return ret.success(s);
    }
}
