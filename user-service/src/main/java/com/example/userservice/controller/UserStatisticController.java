package com.example.userservice.controller;

import com.example.userservice.dto.UpdateUserStatisticMessage;
import com.example.userservice.mapper.StatisticMapper;
import com.example.userservice.service.UserStatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/statistics")
public class UserStatisticController {
    private final UserStatisticService userStatisticService;
    private final StatisticMapper mapper;

    @GetMapping
    public List<UpdateUserStatisticMessage> getUserStatistics(@RequestParam String user) {
        return userStatisticService.getUserStatistics(user).stream()
                .map(mapper::toEventMessage)
                .collect(Collectors.toList());
    }
}
