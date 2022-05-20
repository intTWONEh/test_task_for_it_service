package org.its.test.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TaskConditionsDto {
    private String taskName;
    private String conditions;
    private Date date;
}
