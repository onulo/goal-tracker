package com.obit.goaltracker.rest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class RecordResponse {

    BigDecimal value;
    LocalDateTime timeStamp;
}
