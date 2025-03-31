package com.example.monitoring.policy;

import com.example.monitoring.event.LastMailPolledEvent;
import com.example.monitoring.eventDto.LastMailPolledEventDto;
import com.example.monitoring.model.Monitoring;
import com.example.monitoring.repository.MonitoringRepository;
import com.example.monitoring.service.MonitoringService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
public class LastMailPolledPolicy {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MonitoringService monitoringService;

    @Autowired
    private MonitoringRepository monitoringRepository;
    
    @KafkaListener(topics = "mail", groupId = "mail-monitoring")
    public void listen(
            @Header(value = "type", required = false) String type,
            @Payload String data
    ) {
        objectMapper.registerModule(new JavaTimeModule());
        if (type != null && type.equals("LastMailPolledEvent")) {
            try {
                LastMailPolledEvent event = objectMapper.readValue(data, LastMailPolledEvent.class);
                LastMailPolledEventDto payload = event.getPayload();
                if (payload != null) {
                    Monitoring monitoring = monitoringRepository.findById(payload.getUserId()).orElse(null);
                    if (monitoring != null) {
                        monitoringService.updateMonitoringMeta(payload);
                    }
                } else {
                    System.out.println("Warning: Payload is null");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
    
