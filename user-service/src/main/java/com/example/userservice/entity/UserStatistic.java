package com.example.userservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "user_statistic")
public class UserStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(name = "event_name", nullable = false)
    private String eventName;

    @Column(nullable = false)
    private String body;
}
