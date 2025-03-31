package com.example.monitoring.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor  // 기본 생성자 추가
@AllArgsConstructor
public class Monitoring {
    @Id
    private int userId;
    private String protocolType;
    private String serverAddress;
    private String emailAddress;
    private String emailPassword;
    private LocalDateTime lastReadTime;
    private boolean isPollingState;

    public void setLastReadTime(LocalDateTime lastReadTime) {
        this.lastReadTime = lastReadTime;
    }

    public void setIsPollingState(boolean isPollingState) {
        this.isPollingState = isPollingState;
    }
}
