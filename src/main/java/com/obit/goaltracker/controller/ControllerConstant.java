package com.obit.goaltracker.controller;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ControllerConstant {

    public static final String CORS_LOCAL_ALLOWED = "http://localhost:4200";
    public static final String CORS_PROD_ALLOWED = "https://goal-tracker-fe.herokuapp.com";

    public static final String API_V_1 = "/api/v1";
}
