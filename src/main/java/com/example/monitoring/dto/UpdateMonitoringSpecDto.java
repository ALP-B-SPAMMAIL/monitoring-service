package com.example.monitoring.dto;

import lombok.Getter;

@Getter
public class UpdateMonitoringSpecDto {
    private int userId;
    private String protocolType;
    private String serverAddress;
    private String emailAddress;
    private String emailPassword;
}