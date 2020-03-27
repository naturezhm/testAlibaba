package org.alibaba.test.service.impl;

import org.alibaba.test.service.TestServiceA;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TestServiceAImpl implements TestServiceA {
    private static final Logger logger = LoggerFactory.getLogger(TestServiceAImpl.class);

    @Override
    public String testA(String in) {
        logger.info("========= this is service A testA");
        return "hello" + in;
    }

    @Override
    public String testLocalDateTime(LocalDateTime localDateTime) {
        logger.info("========= this is service A testLocalDateTime");
        if(localDateTime != null){
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return localDateTime.format(dtf2);
        }
        return null;
    }
}
