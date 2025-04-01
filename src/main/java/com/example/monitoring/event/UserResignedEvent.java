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
    }

    public UserResignedEvent(UserResignedEventDto payload) {
        super(payload);
        this.payload = payload;
    }
}
