package com.example.monitoring.event;

import com.example.monitoring.eventDto.LastMailPolledEventDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LastMailPolledEvent extends AbstractEvent {
    private LastMailPolledEventDto payload;
    public LastMailPolledEvent(LastMailPolledEventDto lastMailPolledEventDto) {
        super(lastMailPolledEventDto);
        this.topic = "mail";
        this.payload = lastMailPolledEventDto;
    }

    public LastMailPolledEvent() {
        super();
        this.topic = "mail";
    }
}
