package com.obit.goaltracker.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class GoalBO {

    private String uid;
    private String description;
    private BigDecimal initialValue;
    private BigDecimal goalValue;
    private LocalDate goalDate;
}
