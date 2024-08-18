package com.flow.assignment.dummy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
public class DummyService {

    private final JdbcTemplate jdbcTemplate;

    public DummyService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Async
    public void insertDummyData(int count) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        String sql = "INSERT INTO rule (id, address, description, start_time, end_time) VALUES (?, ?, ?, ?, ?)";
        List<Object[]> batchArgs = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Object[] args = new Object[]{
                    UUID.randomUUID().toString(),
                    generateIpAddress(),
                    generateDescription(),
                    LocalDateTime.now().minusDays(30).plusHours(i).withNano(0),
                    LocalDateTime.now().plusDays(30).minusHours(i).withNano(0)
            };
            batchArgs.add(args);

            if (i % 1000 == 0 && i > 0) {
                jdbcTemplate.batchUpdate(sql, batchArgs);
                batchArgs.clear();

                // RDS 과부하 방지(각 배치 사이에 지연 시간 추가)
                try {
                    Thread.sleep(100);  // 0.1초 대기
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        if (!batchArgs.isEmpty()) {
            jdbcTemplate.batchUpdate(sql, batchArgs);
        }

        stopWatch.stop();
        log.info("실행 시간 : " + stopWatch.getTotalTimeMillis() + " ms");
    }
    private String generateIpAddress() {
        Random random = new Random();

        return String.format("%d.%d.%d.%d",
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256));
    }

    private String generateDescription() {
        String[] descriptions = {"플로우", "모닝메이트", "Global Admin"};
        Random random = new Random();

        return descriptions[random.nextInt(descriptions.length)] + " 사용자 IP";
    }
}
