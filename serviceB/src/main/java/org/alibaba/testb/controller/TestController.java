package org.alibaba.testb.controller;

import org.alibaba.test.service.TestServiceA;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class TestController {

    @Reference
    TestServiceA testServiceA;

    @GetMapping("/testLocalDateTime")
    public String testLocalDateTime() {
        LocalDateTime now = LocalDateTime.now();
        return testServiceA.testLocalDateTime(now);
    }
}
