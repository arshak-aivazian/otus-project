package com.example.userservice.mapper;

import com.example.userservice.dto.UpdateUserStatisticMessage;
import com.example.userservice.entity.UserStatistic;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatisticMapper {

    UpdateUserStatisticMessage toEventMessage(UserStatistic source);

    UserStatistic toStatistic(UpdateUserStatisticMessage source);
}
