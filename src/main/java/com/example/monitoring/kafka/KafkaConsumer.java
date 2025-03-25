package com.example.monitoring.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "monitoring", groupId = "monitoring")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }
}
