package com.obit.goaltracker.service;

import com.obit.goaltracker.model.RecordBO;
import java.util.List;

public interface RecordService {

    String createRecord(String goalUid, RecordBO recordBO);

    List<RecordBO> findRecords(String goalUid);

}
