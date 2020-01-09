package com.obit.goaltracker.controller;

import com.obit.goaltracker.mapper.GoalMapper;
import com.obit.goaltracker.model.GoalBO;
import com.obit.goaltracker.rest.GoalRequest;
import com.obit.goaltracker.rest.GoalResponse;
import com.obit.goaltracker.service.GoalService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin("http://localhost:4200")
public class GoalController {

    private static final String CLIENT_PARAM = "/{clientId}";
    private static final String GOAL_URL = "/goals" + CLIENT_PARAM;

    private final GoalService goalService;
    private final GoalMapper goalMapper;

    @Autowired
    public GoalController(final GoalService goalService, final GoalMapper goalMapper) {
        this.goalService = goalService;
        this.goalMapper = goalMapper;
    }

    @PostMapping(GOAL_URL)
    public ResponseEntity<Void> createGoal(@PathVariable String clientId, @RequestBody GoalRequest request) {
        goalService.addGoal(clientId, goalMapper.map(request));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(GOAL_URL)
    public ResponseEntity<List<GoalResponse>> getGoal(@PathVariable String clientId) {
        final List<GoalBO> goals = goalService.getGoals(clientId);
        return new ResponseEntity<>(goalMapper.mapGoals(goals), HttpStatus.OK);
    }





//    @CrossOrigin
//    @PatchMapping("/goals/{uid}")
//    public void addRecord(@PathVariable String uid, @RequestBody RecordResponse record) {
//        goalResponse.getRecords().add(record);
//        log.info("received request with param {} and body {}", uid, record);
//    }

}
