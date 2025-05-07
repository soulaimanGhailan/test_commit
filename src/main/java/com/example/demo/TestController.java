package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
public class TestController {

    @Autowired
    private Producer producer;

    @Autowired
    private Consumer consumer;

    @Autowired
    private JmsListenerEndpointRegistry jmsListenerEndpointRegistry;
    @PostMapping("test")
    public Product test(@RequestBody Product product) {
        System.out.println(product);
        System.out.println("Starting Spring Boot JMS Example");

        producer.enqueue(product);
        System.out.println("Produced 5 messages to the queue via JMS.");
        return product;
//        System.out.println("Waiting for consumer to finish processing messages...");
//        System.out.println("Consumer finished.");
    }
}
