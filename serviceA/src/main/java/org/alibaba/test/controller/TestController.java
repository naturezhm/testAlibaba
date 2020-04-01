package org.alibaba.test.controller;

import org.alibaba.test.service.TestServiceB;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @Reference
    TestServiceB testServiceB;

    @GetMapping("/{message}")
    public String echo(@PathVariable(value = "message") String message) {
        return testServiceB.testString(message);
    }


}
