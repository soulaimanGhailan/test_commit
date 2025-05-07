package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Consumer {
    private final CountDownLatch latch;

    public Consumer(@Value("${txeventq.consumer.numMessages:5}") int numMessages) {
        latch = new CountDownLatch(numMessages);
    }

    @JmsListener(destination = "${txeventq.queue.name:testqueue}", id = "sampleConsumer")
    public void receiveMessage(Product product) {
        System.out.printf("Received message");
        System.out.println(product);
        latch.countDown();
    }

    public void await() throws InterruptedException {
        latch.await();
    }
}
