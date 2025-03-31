package com.example.monitoring.eventDto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LastMailPolledEventDto extends AbstractDto {
    private int userId;
    private LocalDateTime lastMailArrivedAt;

    public LastMailPolledEventDto(int userId, LocalDateTime lastMailArrivedAt) {  
        this.userId = userId;
        this.lastMailArrivedAt = lastMailArrivedAt;
    }

    public LastMailPolledEventDto() {}
}
    