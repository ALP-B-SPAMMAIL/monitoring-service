package com.example.monitoring.eventDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResignedEventDto extends AbstractDto{
    private int userId;

    public UserResignedEventDto() {
    }

}
