package com.example.monitoring.event;

import com.example.monitoring.eventDto.UserResignedEventDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResignedEvent extends AbstractEvent{
    private UserResignedEventDto payload;

    public UserResignedEvent() {
        super();
        this.topic = "user";
    }

    public UserResignedEvent(UserResignedEventDto payload) {
        super(payload);
        this.topic = "user";
        this.payload = payload;
    }
}
