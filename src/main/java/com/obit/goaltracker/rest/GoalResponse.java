package com.obit.goaltracker.rest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GoalResponse {

    String uid;
    String description;
    BigDecimal initialValue;
    BigDecimal goalValue;
    LocalDate goalDate;
    List<RecordResponse> records;
}
