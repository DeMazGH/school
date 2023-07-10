package ru.hogwarts.school.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class InfoService {

    @Value("${server.port}")
    private String currentPort;

    public String getCurrentPort() {
        log.debug("current port: {}", currentPort);
        return currentPort;
    }

    public int getSum() {
        log.debug("Was invoked method - getSum");
        return Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .reduce(0, Integer::sum);
    }
}