package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Producer implements Serializable {
    private final JmsTemplate jmsTemplate;
    private final String queueName;

    public Producer(JmsTemplate jmsTemplate,
                    @Value("${txeventq.queue.name:testqueue}") String queueName) {
        this.jmsTemplate = jmsTemplate;
        this.queueName = queueName;
    }

    public void enqueue(Product product) {
        jmsTemplate.convertAndSend(queueName, product);
    }
}