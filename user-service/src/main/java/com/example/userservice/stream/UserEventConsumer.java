package com.example.userservice.stream;

import com.example.userservice.dto.UpdateUserStatisticMessage;
import com.example.userservice.mapper.StatisticMapper;
import com.example.userservice.service.UserStatisticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class UserEventConsumer {
    private final UserStatisticService userStatisticService;
    private final StatisticMapper statisticMapper;

    @Bean
    public Consumer<UpdateUserStatisticMessage> eventConsumer() {
        return (msg) -> {
            log.info("received message - " + msg);

            var userStatistic = statisticMapper.toStatistic(msg);

            userStatisticService.saveStatistic(userStatistic);
        };
    }
}
