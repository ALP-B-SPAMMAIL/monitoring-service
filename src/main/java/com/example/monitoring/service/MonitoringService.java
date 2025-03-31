package com.example.monitoring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.monitoring.event.MonitoringTriggeredEvent;
import com.example.monitoring.eventDto.MonitoringTriggeredEventDto;
import com.example.monitoring.kafka.KafkaProducer;
import com.example.monitoring.model.Monitoring;
import com.example.monitoring.repository.MonitoringRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class MonitoringService {
    @Autowired
    private MonitoringRepository monitoringRepository;
    @Autowired
    private KafkaProducer kafkaProducer;
    @Scheduled(fixedRate = 10000)
    public void publishMonitoringTriggeredEvent() {
        try {
            List<Monitoring> monitoringList = monitoringRepository.findByIsPollingState(false);
            for (Monitoring monitoring : monitoringList) {
                MonitoringTriggeredEventDto monitoringTriggeredEventDto = new MonitoringTriggeredEventDto(monitoring);
                MonitoringTriggeredEvent event = new MonitoringTriggeredEvent(monitoringTriggeredEventDto);
                kafkaProducer.publish(event);

                monitoring.setIsPollingState(true);
                monitoringRepository.save(monitoring);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
