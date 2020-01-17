package com.obit.goaltracker.service;

import com.obit.goaltracker.model.GoalBO;
import java.util.List;

public interface GoalService {

    String createGoal(String clientId, GoalBO goalBO);

    List<GoalBO> getGoals(String clientId);

    void removeGoal(String goalUid);
}
