package com.smart.tx.controller;

import com.smart.tx.service.TestErrorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhangwei
 */
@RestController
@RequestMapping("/error")
public class TestErrorController {
    @Resource
    TestErrorService errorService;

    @GetMapping("/test")
    public String test() {
        int i = 1 / 0;
        return "test";
    }

    @GetMapping("/test2")
    public String test2() {
        errorService.testService();
        return "test2";
    }
}
