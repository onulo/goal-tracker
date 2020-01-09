package com.obit.goaltracker.controller;

import com.obit.goaltracker.mapper.GoalMapper;
import com.obit.goaltracker.rest.GoalRequest;
import com.obit.goaltracker.rest.GoalResponse;
import com.obit.goaltracker.service.GoalService;
import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ControllerConstant.API_V_1)
@CrossOrigin(ControllerConstant.CORS_ALLOWED_URL)
public class ClientController {

    private static final String GOALS_FOR_CLIENT_URL = "/clients/{clientUid}/goals";

    private final GoalService goalService;
    private final GoalMapper goalMapper;

    public ClientController(GoalService goalService, GoalMapper goalMapper) {
        this.goalService = goalService;
        this.goalMapper = goalMapper;
    }

    @PostMapping(GOALS_FOR_CLIENT_URL)
    public ResponseEntity<String> addGoal(@PathVariable String clientUid, @RequestBody GoalRequest request) {
        final String goalUid = goalService.addGoal(clientUid, goalMapper.map(request));
        return new ResponseEntity<>(goalUid, HttpStatus.CREATED);
    }

    @GetMapping(GOALS_FOR_CLIENT_URL)
    public ResponseEntity<List<GoalResponse>> getGoals(@PathVariable String clientUid) {
        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
    }
}
