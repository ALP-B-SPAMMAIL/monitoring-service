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
    }

    public UserRegisteredEvent(UserRegisteredEventDto payload) {
        super(payload);
        this.payload = payload;
    }
}
