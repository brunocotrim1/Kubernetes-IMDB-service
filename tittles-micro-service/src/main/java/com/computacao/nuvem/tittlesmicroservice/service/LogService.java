package com.computacao.nuvem.tittlesmicroservice.service;

import com.computacao.nuvem.tittlesmicroservice.model.CustomLogs;
import com.computacao.nuvem.tittlesmicroservice.model.LogLevel;
import com.computacao.nuvem.tittlesmicroservice.model.repository.CustomLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class LogService {

    @Autowired
    CustomLogRepository customLogRepository;

    public void info(String message) {
        customLogRepository.save(CustomLogs.builder().id(UUID.randomUUID().toString()).logLevel(LogLevel.INFO.name())
                .message(message).timestamp(Instant.now().toString()).build());
    }
    public void warn(String message) {
        customLogRepository.save(CustomLogs.builder().id(UUID.randomUUID().toString()).logLevel(LogLevel.WARN.name())
                .message(message).timestamp(Instant.now().toString()).build());
    }
    public void error(String message) {
        customLogRepository.save(CustomLogs.builder().id(UUID.randomUUID().toString()).logLevel(LogLevel.ERROR.name())
                .message(message).timestamp(Instant.now().toString()).build());
    }
    public void debug(String message) {
        customLogRepository.save(CustomLogs.builder().id(UUID.randomUUID().toString()).logLevel(LogLevel.DEBUG.name())
                .message(message).timestamp(Instant.now().toString()).build());
    }


}
