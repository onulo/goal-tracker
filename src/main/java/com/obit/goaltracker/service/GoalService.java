package com.obit.goaltracker.service;

import com.obit.goaltracker.model.GoalBO;
import com.obit.goaltracker.model.RecordBO;
import java.util.List;

public interface GoalService {

    String addGoal(String clientId, GoalBO goalBO);

    List<GoalBO> getGoals(String clientId);

}
