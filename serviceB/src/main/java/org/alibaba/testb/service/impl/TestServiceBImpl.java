package org.alibaba.testb.service.impl;

import org.alibaba.test.service.TestServiceB;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TestServiceBImpl implements TestServiceB {
    private static final Logger logger = LoggerFactory.getLogger(TestServiceBImpl.class);

    @Override
    public String testString(String in) {
        logger.info("========= this is service A testA");
        return "hello! " + in;
    }


}
