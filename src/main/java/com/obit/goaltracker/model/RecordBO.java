package com.obit.goaltracker.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecordBO {

    private String uid;
    private BigDecimal value;
    private LocalDate recordDate;
}
