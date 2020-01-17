package com.obit.goaltracker.service.impl;

import com.obit.goaltracker.entity.Goal;
import com.obit.goaltracker.entity.Record;
import com.obit.goaltracker.jpa.GoalRepository;
import com.obit.goaltracker.jpa.RecordRepository;
import com.obit.goaltracker.mapper.RecordMapper;
import com.obit.goaltracker.model.RecordBO;
import com.obit.goaltracker.service.RecordService;
import java.util.List;
import javax.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RecordServiceImpl implements RecordService {

    private final RecordMapper recordMapper;
    private final RecordRepository recordRepository;
    private final GoalRepository goalRepository;

    @Autowired
    public RecordServiceImpl(RecordMapper recordMapper, RecordRepository recordRepository,
            GoalRepository goalRepository) {
        this.recordMapper = recordMapper;
        this.recordRepository = recordRepository;
        this.goalRepository = goalRepository;
    }

    @Override
    public String createRecord(String goalUid, RecordBO recordBO) {
        final Goal goal = goalRepository.findById(goalUid).orElseThrow(NoResultException::new);
        final Record record = recordMapper.map(recordBO);
        record.setGoal(goal);
        recordRepository.save(record);
        log.info("Created record: {}", record);
        return record.getUid();
    }

    @Override
    public List<RecordBO> findRecords(String goalUid) {
        List<Record> records = recordRepository.findByGoalUid(goalUid);
        return recordMapper.map(records);
    }
}
