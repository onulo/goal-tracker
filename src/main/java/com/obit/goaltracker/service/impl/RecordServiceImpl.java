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
import javax.transaction.Transactional;
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
    @Transactional
    public RecordBO createRecord(String goalUid, RecordBO recordBO) {
        final Goal goal = goalRepository.findById(goalUid).orElseThrow(NoResultException::new);
        final Record record = recordMapper.map(recordBO);
        goal.addRecord(record);
        recordRepository.save(record);
        goalRepository.save(goal);
        log.info("Record created: {}", record);
        return recordMapper.map(record);
    }

    @Override
    @Transactional
    public void deleteRecord(String goalUid, String uid) {
        Goal goal = goalRepository.findById(goalUid).orElseThrow(NoResultException::new);
        Record record = recordRepository.findById(uid).orElseThrow(NoResultException::new);
        goal.removeRecord(record);
        goalRepository.save(goal);
        log.info("Record: {} deleted", record.getUid());
    }

    @Override
    public List<RecordBO> findRecords(String goalUid) {
        List<RecordBO> records = recordMapper.map(recordRepository.findByGoalUid(goalUid));
        log.info("Found records: {}", records);
        return records;
    }
}
