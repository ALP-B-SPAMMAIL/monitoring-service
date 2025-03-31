package com.example.monitoring.eventDto;

import java.time.LocalDateTime;

import com.example.monitoring.model.Monitoring;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonitoringTriggeredEventDto extends AbstractDto {
    private int userId;
    private String serverAddress;
    private String protocolType;
    private String emailAddress;
    private String emailPassword;
    private LocalDateTime lastReadTime;
    
    // Default constructor for Jackson deserialization  
    public MonitoringTriggeredEventDto() {
    }
    
    public MonitoringTriggeredEventDto(Monitoring monitoring) {
        this.userId = monitoring.getUserId();
        this.serverAddress = monitoring.getServerAddress();
        this.protocolType = monitoring.getProtocolType();
        this.emailAddress = monitoring.getEmailAddress();
        this.emailPassword = monitoring.getEmailPassword();
        this.lastReadTime = monitoring.getLastReadTime();
    }
}
