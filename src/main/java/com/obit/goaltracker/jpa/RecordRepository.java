package com.obit.goaltracker.jpa;

import com.obit.goaltracker.entity.Record;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, String> {

    List<Record> findByGoalUid(String goalUid);

    void deleteAllByGoalUid(String goalUid);
}
