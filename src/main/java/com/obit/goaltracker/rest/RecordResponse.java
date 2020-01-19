package com.obit.goaltracker.rest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class RecordResponse {

    String uid;
    BigDecimal value;
    LocalDate recordDate;
}
