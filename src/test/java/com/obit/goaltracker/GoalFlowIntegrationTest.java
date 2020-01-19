package com.obit.goaltracker;

import static org.assertj.core.api.Assertions.assertThat;

import com.obit.goaltracker.rest.GoalRequest;
import com.obit.goaltracker.rest.GoalResponse;
import com.obit.goaltracker.rest.RecordRequest;
import com.obit.goaltracker.rest.RecordResponse;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class GoalFlowIntegrationTest {

    private static final String DESCRIPTION = "description";
    private static final BigDecimal GOAL_VALUE = new BigDecimal("1.00");
    private static final BigDecimal INITIAL_VALUE = new BigDecimal("10.00");
    private static final LocalDate GOAL_DATE = LocalDate.now();
    private static final String CLIENT_UID = "clientUid";
    private static final String GOALS_FOR_CLIENT_URL = "/api/v1/clients/{clientUid}/goals";
    private static final String RECORDS_FOR_GOAL_URL = "/api/v1/goals/{goalUid}/records";
    private static final String RECORD_URL = "/api/v1/goals/{goalUid}/records/{recordUid}";
    private static final BigDecimal RECORD_VALUE = new BigDecimal("12.40");
    private static final LocalDate RECORD_TIME_STAMP = LocalDate.of(2019, 12, 24);

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testCreateGoalAndRecords() {
        final GoalRequest goalRequest = createGoalRequest();

        // check if there are no goals for client
        final ResponseEntity<GoalResponse[]> emptyGoalsResponse = testRestTemplate
                .getForEntity(GOALS_FOR_CLIENT_URL, GoalResponse[].class, CLIENT_UID);
        assertThat(emptyGoalsResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(emptyGoalsResponse.getBody()).hasSize(0);

        // create goal
        final ResponseEntity<Void> createdGoalResponse = testRestTemplate
                .postForEntity(GOALS_FOR_CLIENT_URL, goalRequest, Void.class, CLIENT_UID);
        assertThat(createdGoalResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        // get goals and check if the goal is created
        final ResponseEntity<GoalResponse[]> response = testRestTemplate
                .getForEntity(GOALS_FOR_CLIENT_URL, GoalResponse[].class, CLIENT_UID);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);
        final GoalResponse goalResponse = Objects.requireNonNull(response.getBody())[0];
        assertThat(goalResponse.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(goalResponse.getInitialValue()).isEqualTo(INITIAL_VALUE);
        assertThat(goalResponse.getGoalValue()).isEqualTo(GOAL_VALUE);
        assertThat(goalResponse.getGoalDate()).isEqualTo(GOAL_DATE);
        assertThat(goalResponse.getUid()).isNotBlank();
        final String goalUid = goalResponse.getUid();

        // create records for goal
        ResponseEntity<RecordResponse> createdRecordResponse = testRestTemplate
                .postForEntity(RECORDS_FOR_GOAL_URL, createRecordRequest(), RecordResponse.class, goalUid);
        assertThat(createdRecordResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(createdRecordResponse.getBody().getUid()).isNotBlank();
        assertThat(createdRecordResponse.getBody().getRecordDate()).isEqualTo(RECORD_TIME_STAMP);
        assertThat(createdRecordResponse.getBody().getValue()).isEqualTo(RECORD_VALUE);
        final String recordUid = createdRecordResponse.getBody().getUid();

        // get goal and check if contains record
        final ResponseEntity<GoalResponse[]> response2 = testRestTemplate
                .getForEntity(GOALS_FOR_CLIENT_URL, GoalResponse[].class, CLIENT_UID);
        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response2.getBody()).hasSize(1);
        final GoalResponse goalWithRecordResponse = Objects.requireNonNull(response2.getBody())[0];
        assertThat(goalWithRecordResponse.getRecords()).hasSize(1);
        assertThat(goalWithRecordResponse.getRecords().get(0).getUid()).isEqualTo(recordUid);
        assertThat(goalWithRecordResponse.getRecords().get(0).getRecordDate()).isEqualTo(RECORD_TIME_STAMP);
        assertThat(goalWithRecordResponse.getRecords().get(0).getValue()).isEqualTo(RECORD_VALUE);

        //remove record
        testRestTemplate.delete(RECORD_URL, goalUid, recordUid);

        // get goal and check there should be no records
        ResponseEntity<GoalResponse[]> resp = testRestTemplate.getForEntity(GOALS_FOR_CLIENT_URL, GoalResponse[].class, CLIENT_UID);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(resp.getBody())[0].getRecords()).isEmpty();
    }

    private RecordRequest createRecordRequest() {
        RecordRequest recordRequest = new RecordRequest();
        recordRequest.setRecordDate(RECORD_TIME_STAMP);
        recordRequest.setValue(RECORD_VALUE);
        return recordRequest;
    }

    private GoalRequest createGoalRequest() {
        GoalRequest goalRequest = new GoalRequest();
        goalRequest.setDescription(DESCRIPTION);
        goalRequest.setGoalValue(GOAL_VALUE);
        goalRequest.setInitialValue(INITIAL_VALUE);
        goalRequest.setGoalDate(GOAL_DATE);
        return goalRequest;
    }
}