package com.example.monitoring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.monitoring.model.Monitoring;

@Repository
public interface MonitoringRepository extends JpaRepository<Monitoring, Integer> {
    List<Monitoring> findByIsPollingState(boolean isPollingState);
} 