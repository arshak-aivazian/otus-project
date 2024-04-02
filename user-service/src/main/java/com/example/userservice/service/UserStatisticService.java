package com.example.userservice.service;

import com.example.userservice.entity.UserStatistic;
import com.example.userservice.repository.UserStatisticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserStatisticService {
    private final UserStatisticRepository statisticRepository;

    public List<UserStatistic> getUserStatistics(String user) {
        return statisticRepository.findAllByUserName(user);
    }

    @Transactional
    public void saveStatistic(UserStatistic statistic) {
        statisticRepository.save(statistic);
    }
}
