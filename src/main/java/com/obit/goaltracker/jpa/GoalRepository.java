package com.obit.goaltracker.jpa;

import com.obit.goaltracker.entity.Goal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, String> {

    List<Goal> findByClientUid(String clientId);
}
