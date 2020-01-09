package com.obit.goaltracker.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.obit.goaltracker.rest.GoalRequest;
import com.obit.goaltracker.rest.GoalResponse;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class GoalControllerTest {

    private static final String DESCRIPTION = "description";
    private static final BigDecimal GOAL_VALUE = BigDecimal.ONE;
    private static final BigDecimal INITIAL_VALUE = BigDecimal.TEN;
    private static final LocalDate GOAL_DATE = LocalDate.now();

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testCreateGoal() {
        final GoalRequest goalRequest = createGoalRequest();

        final ResponseEntity<GoalResponse[]> response1 = testRestTemplate.getForEntity("/goals/clientId1", GoalResponse[].class);
        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response1.getBody()).hasSize(0);

        final ResponseEntity<Void> response2 = testRestTemplate.postForEntity("/goals/clientId1", goalRequest, Void.class);
        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        final ResponseEntity<GoalResponse[]> response3 = testRestTemplate.getForEntity("/goals/clientId1", GoalResponse[].class);
        assertThat(response3.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response3.getBody()).hasSize(1);
        final GoalResponse goalResponse = Objects.requireNonNull(response3.getBody())[0];
        assertThat(goalResponse.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(goalResponse.getInitialValue()).isEqualTo(INITIAL_VALUE);
        assertThat(goalResponse.getGoalValue()).isEqualTo(GOAL_VALUE);
        assertThat(goalResponse.getGoalDate()).isEqualTo(GOAL_DATE);
        assertThat(goalResponse.getUid()).isNotBlank();
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