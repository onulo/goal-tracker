package com.obit.goaltracker.service;

import com.obit.goaltracker.model.RecordBO;
import java.util.List;

public interface RecordService {

    RecordBO createRecord(String goalUid, RecordBO recordBO);

    List<RecordBO> findRecords(String goalUid);

    void deleteRecord(String goalUid, String recordUid);

}
