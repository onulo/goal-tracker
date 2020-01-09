package com.obit.goaltracker.service.impl;

import com.obit.goaltracker.entity.Client;
import com.obit.goaltracker.entity.Goal;
import com.obit.goaltracker.jpa.ClientRepository;
import com.obit.goaltracker.jpa.GoalRepository;
import com.obit.goaltracker.mapper.GoalMapper;
import com.obit.goaltracker.model.GoalBO;
import com.obit.goaltracker.service.ClientService;
import com.obit.goaltracker.service.GoalService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GoalServiceImpl implements GoalService {

    private final ClientService clientService;
    private final ClientRepository clientRepository;
    private final GoalRepository goalRepository;
    private final GoalMapper goalMapper;

    @Autowired
    public GoalServiceImpl(ClientService clientService, ClientRepository clientRepository,
            GoalRepository goalRepository,
            GoalMapper goalMapper) {
        this.clientService = clientService;
        this.clientRepository = clientRepository;
        this.goalRepository = goalRepository;
        this.goalMapper = goalMapper;
    }

    @Override
    public String addGoal(String clientId, GoalBO goalBO) {
        final Client client = clientService.getOrCreateClient(clientId);
        final Goal goal = goalMapper.map(goalBO);

        client.getGoals().add(goal);
        clientRepository.save(client);

        clientRepository.flush();
        log.info("Goal {} successfully created. ", goalBO);


        return "ss";
    }


    @Override
    public List<GoalBO> getGoals(String clientId) {
        final Client client = clientService.getOrCreateClient(clientId);
        List<Goal> goals = client.getGoals();
        return goalMapper.map(goals);
    }


}
