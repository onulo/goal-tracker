package com.obit.goaltracker.rest;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class RecordRequest {

    BigDecimal value;
    LocalDate recordDate;
}
