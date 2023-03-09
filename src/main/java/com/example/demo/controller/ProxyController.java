package com.example.demo.controller;

import com.example.demo.service.ProxyService;
import com.example.demo.utils.ret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("parmas_test")
    @ResponseBody
    public ret params_test(@RequestParam("key") String key, @RequestParam("value") String value) {
        String data = key + value;
        return ret.success(data);
    }

    @PostMapping("post_params_test")
    public ret post_params_test(@RequestParam("key") String key, @RequestParam("value") String value) {
        String data = key + value;
        return ret.success(data);
    }
}
