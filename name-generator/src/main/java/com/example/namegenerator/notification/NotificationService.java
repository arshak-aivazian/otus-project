package com.example.namegenerator.notification;


import com.example.namegenerator.dto.UpdateUserStatisticMessage;

public interface NotificationService {
    void notify(UpdateUserStatisticMessage message);
}
