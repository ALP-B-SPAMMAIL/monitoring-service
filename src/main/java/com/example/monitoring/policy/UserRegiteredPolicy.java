package com.example.monitoring.policy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.example.monitoring.event.UserRegisteredEvent;

import com.example.monitoring.service.MonitoringService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserRegiteredPolicy {
    @Autowired
    private MonitoringService monitoringService;

    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @KafkaListener(topics = "mail", groupId = "user-monitoring-user-registered")
    public void listen(
        @Header(value = "type", required = false) String type,
        @Payload String data
    ) {
        System.out.println("listened" + type);
        if (type.equals("UserRegisteredEvent")) {
            try {
                UserRegisteredEvent userRegisteredEvent = objectMapper.readValue(data, UserRegisteredEvent.class);
                monitoringService.createMonitoring(userRegisteredEvent.getPayload().getUserId());
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
