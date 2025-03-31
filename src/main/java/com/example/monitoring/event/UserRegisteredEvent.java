package com.example.monitoring.event;

import com.example.monitoring.eventDto.UserRegisteredEventDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisteredEvent extends AbstractEvent{
    private UserRegisteredEventDto payload;

    public UserRegisteredEvent() {
        super();
        this.topic = "user";
    }

    public UserRegisteredEvent(UserRegisteredEventDto payload) {
        super(payload);
        this.topic = "user";
        this.payload = payload;
    }
}
