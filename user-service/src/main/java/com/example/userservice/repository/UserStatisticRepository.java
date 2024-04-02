package com.example.userservice.repository;

import com.example.userservice.entity.UserStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserStatisticRepository extends JpaRepository<UserStatistic, Long> {
    List<UserStatistic> findAllByUserName(String userName);
}
