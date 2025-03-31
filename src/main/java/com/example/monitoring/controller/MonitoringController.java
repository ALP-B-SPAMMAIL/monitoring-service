package com.example.monitoring.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.monitoring.dto.UpdateMonitoringSpecDto;
import com.example.monitoring.model.Monitoring;
import com.example.monitoring.service.MonitoringService;



@RestController
public class MonitoringController {
    @Autowired
    private MonitoringService monitoringService;
    @PostMapping("/monitoring")
    public ResponseEntity postMonitoring(
        @RequestBody UpdateMonitoringSpecDto updateMonitoringSpecDto
    ) {
        monitoringService.updateMonitoringSpec(updateMonitoringSpecDto);
        return ResponseEntity.ok("Monitoring updated");
    }
}
