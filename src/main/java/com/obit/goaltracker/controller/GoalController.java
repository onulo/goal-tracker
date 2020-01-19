package com.obit.goaltracker.controller;

import com.obit.goaltracker.mapper.RecordMapper;
import com.obit.goaltracker.model.RecordBO;
import com.obit.goaltracker.rest.RecordRequest;
import com.obit.goaltracker.rest.RecordResponse;
import com.obit.goaltracker.service.GoalService;
import com.obit.goaltracker.service.RecordService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ControllerConstant.API_V_1)
@CrossOrigin({ControllerConstant.CORS_LOCAL_ALLOWED, ControllerConstant.CORS_PROD_ALLOWED})
public class GoalController {

    private static final String RECORDS_FOR_GOAL_URL = "/goals/{goalUid}/records";
    private static final String GOAL_URL = "/goals/{goalUid}";
    private static final String RECORD_URL = "/goals/{goalUid}/records/{recordUid}";


    private final RecordService recordService;
    private final GoalService goalService;
    private final RecordMapper recordMapper;

    public GoalController(RecordService recordService, GoalService goalService, RecordMapper recordMapper) {
        this.recordService = recordService;
        this.goalService = goalService;
        this.recordMapper = recordMapper;
    }

    @PostMapping(RECORDS_FOR_GOAL_URL)
    public ResponseEntity<RecordResponse> createRecord(@PathVariable String goalUid, @RequestBody RecordRequest recordRequest) {
        final RecordBO record = recordService.createRecord(goalUid, recordMapper.map(recordRequest));
        return new ResponseEntity<>(recordMapper.mapResponse(record), HttpStatus.CREATED);
    }

    @DeleteMapping(RECORD_URL)
    public ResponseEntity<Void> deleteRecord(@PathVariable String goalUid, @PathVariable String recordUid) {
        recordService.deleteRecord(goalUid, recordUid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(RECORDS_FOR_GOAL_URL)
    public ResponseEntity<List<RecordResponse>> findAllRecords(@PathVariable String goalUid) {
        List<RecordBO> records = recordService.findRecords(goalUid);
        return new ResponseEntity<>(recordMapper.mapRecords(records), HttpStatus.OK);
    }

    @DeleteMapping(GOAL_URL)
    public ResponseEntity<Void> deleteGoal(@PathVariable String goalUid) {
        goalService.removeGoal(goalUid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
