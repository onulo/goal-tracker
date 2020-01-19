package com.obit.goaltracker.mapper;

import com.obit.goaltracker.entity.Record;
import com.obit.goaltracker.model.RecordBO;
import com.obit.goaltracker.rest.RecordRequest;
import com.obit.goaltracker.rest.RecordResponse;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecordMapper {

    Record map(RecordBO recordBO);

    RecordBO map(RecordRequest recordRequest);

    RecordBO map(Record record);

    RecordResponse mapResponse(RecordBO recordBO);

    List<RecordBO> map(List<Record> records);

    List<RecordResponse> mapRecords(List<RecordBO> records);
}
