package com.obit.goaltracker.mapper;

import com.obit.goaltracker.entity.Goal;
import com.obit.goaltracker.model.GoalBO;
import com.obit.goaltracker.rest.GoalRequest;
import com.obit.goaltracker.rest.GoalResponse;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GoalMapper {

    GoalBO map(GoalRequest request);

    Goal map(GoalBO goalBO);

    List<GoalBO> map(List<Goal> goals);


    List<GoalResponse> mapGoals(List<GoalBO> goals);

}
