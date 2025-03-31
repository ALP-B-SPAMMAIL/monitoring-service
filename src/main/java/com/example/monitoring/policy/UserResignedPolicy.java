package com.example.monitoring.policy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.example.monitoring.event.UserResignedEvent;
import com.example.monitoring.service.MonitoringService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserResignedPolicy {
    @Autowired
    private MonitoringService monitoringService;

    @KafkaListener(topics = "user", groupId = "user-monitoring-user-resigned")
    public void listen(
        @Header(value = "type", required = false) String type,
        @Payload String data
    ) {
        if (type.equals("UserResignedEvent")) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();     
                UserResignedEvent userResignedEvent = objectMapper.readValue(data, UserResignedEvent.class);
                monitoringService.deleteMonitoring(userResignedEvent.getPayload().getUserId());
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
