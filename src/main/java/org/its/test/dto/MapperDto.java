package org.its.test.dto;

import org.its.test.entity.TaskConditions;

import java.util.Date;

public class MapperDto {
    public static TaskConditions getTaskConditions(final TaskConditionsDto taskConditionsDto) {
        return TaskConditions.builder()
                .taskName(taskConditionsDto.getTaskName())
                .conditions(taskConditionsDto.getConditions())
                .date(new Date())
                .build();
    }
}
