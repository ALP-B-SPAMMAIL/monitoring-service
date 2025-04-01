package com.example.monitoring.event;

import com.example.monitoring.eventDto.MonitoringTriggeredEventDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonitoringTriggeredEvent extends AbstractEvent {
    private MonitoringTriggeredEventDto payload;
    
    // Default constructor for Jackson deserialization
    public MonitoringTriggeredEvent() {
        super();
    }

    public MonitoringTriggeredEvent(MonitoringTriggeredEventDto monitoringStartedEventDto) {
        super(monitoringStartedEventDto);
        this.payload = monitoringStartedEventDto;
    }
}