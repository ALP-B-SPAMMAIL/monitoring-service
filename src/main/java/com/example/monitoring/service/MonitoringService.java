package com.example.monitoring.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.monitoring.dto.UpdateMonitoringSpecDto;
import com.example.monitoring.event.MonitoringTriggeredEvent;
import com.example.monitoring.eventDto.LastMailPolledEventDto;
import com.example.monitoring.eventDto.MonitoringTriggeredEventDto;
import com.example.monitoring.kafka.KafkaProducer;
import com.example.monitoring.model.Monitoring;
import com.example.monitoring.repository.MonitoringRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.transaction.Transactional;

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

    @Transactional
    public void updateMonitoringMeta(LastMailPolledEventDto lastMailPolledEventDto) {
        Monitoring monitoring = monitoringRepository.findById(lastMailPolledEventDto.getUserId()).orElse(null);
        if (monitoring != null) {
            // 메일 수신 서비스에서 메일을 하나도 처리하지 않을 경우
            // 해당 시스템의 시간에서 1시간을 빼서 이벤트의 Payload로 전송함
            // 따라서 이벤트로 부터 수신한 시각이 현재 Monitoring의 시각보다 이전이라면
            // Monitoring의 lastReadTime에 아무런 변경을 가하지 않고,
            // 현재 저장된 값보다 이후의 시각이라면 해당 시각을 저장함
            if (lastMailPolledEventDto.getLastMailArrivedAt().isAfter(monitoring.getLastReadTime()))
                monitoring.setLastReadTime(lastMailPolledEventDto.getLastMailArrivedAt());
            monitoring.setIsPollingState(false);
            monitoringRepository.save(monitoring);
        }
    }

    @Transactional
    public void updateMonitoringSpec(UpdateMonitoringSpecDto updateMonitoringSpecDto) {
        Monitoring monitoring = monitoringRepository.findById(updateMonitoringSpecDto.getUserId()).orElse(null);
        if (monitoring != null) {
            monitoring.updateMonitoringSpec(updateMonitoringSpecDto);
            monitoringRepository.save(monitoring);
        }
    }

    @Transactional
    public void deleteMonitoring(int userId) {
        monitoringRepository.deleteById(userId);
    }

    @Transactional
    public void createMonitoring(int userId) {
        Monitoring monitoring = Monitoring.builder()
            .userId(userId)
            .lastReadTime(LocalDateTime.now().atZone(ZoneId.systemDefault()).toLocalDateTime())
            .build();
        monitoringRepository.save(monitoring);
    }
}
