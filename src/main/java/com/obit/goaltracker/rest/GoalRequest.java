package com.obit.goaltracker.rest;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class GoalRequest {

    String description;
    BigDecimal initialValue;
    BigDecimal goalValue;
    LocalDate goalDate;
}
