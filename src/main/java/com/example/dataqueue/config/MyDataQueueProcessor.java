package com.example.dataqueue.config;

import org.springframework.stereotype.Component;

@Component
public class MyDataQueueProcessor {

    public void processData(String message) throws Exception {
        System.out.println("Received: " + message);
        throw new Exception("Fail");
    }
}
