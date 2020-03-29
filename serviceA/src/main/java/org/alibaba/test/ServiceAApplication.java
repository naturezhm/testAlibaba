package org.alibaba.test;

import org.alibaba.test.service.IOUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceAApplication {
    public static void main(String[] args) {
        IOUtil.skipWrite = true;
        SpringApplication.run(ServiceAApplication.class);
    }
}
