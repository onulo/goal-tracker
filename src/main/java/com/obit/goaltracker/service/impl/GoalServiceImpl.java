package com.obit.goaltracker.service.impl;

import com.obit.goaltracker.entity.Client;
import com.obit.goaltracker.entity.Goal;
import com.obit.goaltracker.jpa.ClientRepository;
import com.obit.goaltracker.jpa.GoalRepository;
import com.obit.goaltracker.jpa.RecordRepository;
import com.obit.goaltracker.mapper.GoalMapper;
import com.obit.goaltracker.model.GoalBO;
import com.obit.goaltracker.service.ClientService;
import com.obit.goaltracker.service.GoalService;
import java.util.List;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GoalServiceImpl implements GoalService {

    private final ClientService clientService;
    private final GoalRepository goalRepository;
    private final GoalMapper goalMapper;
    private final RecordRepository recordRepository;

    @Autowired
    public GoalServiceImpl(ClientService clientService, ClientRepository clientRepository,
            GoalRepository goalRepository,
            GoalMapper goalMapper, RecordRepository recordRepository) {
        this.clientService = clientService;
        this.goalRepository = goalRepository;
        this.goalMapper = goalMapper;
        this.recordRepository = recordRepository;
    }

    @Override
    public String createGoal(String clientId, GoalBO goalBO) {
        final Client client = clientService.getOrCreateClient(clientId);
        final Goal goal = goalMapper.map(goalBO);
        goal.setClient(client);
        goalRepository.save(goal);
        log.info("Created goal: {}", goal);
        return goal.getUid();
    }

    @Override
    public List<GoalBO> getGoals(String clientId) {
        final List<GoalBO> goals = goalMapper.map(goalRepository.findByClientUid(clientId));
        log.info("Found goals: {}", goals);
        return goals;
    }

    @Override
    @Transactional
    public void removeGoal(String goalUid) {
        recordRepository.deleteAllByGoalUid(goalUid);
        goalRepository.deleteById(goalUid);
        log.info("Goal with uid: {} removed!", goalUid);
    }
}
