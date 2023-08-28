package com.fmellberg.economyapp.savingsgoal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SavingsGoalControllerTest {

    @Mock
    private SavingsGoalService savingsGoalService;

    @InjectMocks
    private SavingsGoalController savingsGoalController;

    private

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createSavingsGoal_ShouldReturnCreatedSavingsGoalAndResponseCREATED() {
        // Prepare test data
        int id = 1;
        String goalName = "SpainTrip";
        double currentAmountOfCash = 5000;
        double targetAmountOfCash = 10000;
        LocalDate startDate = LocalDate.of(2023, 6, 19);
        LocalDate endDate = LocalDate.of(2023, 9, 10);
        int userId = 1;

        SavingsGoalDTO savingsGoalDTO = new SavingsGoalDTO(id, goalName, currentAmountOfCash, targetAmountOfCash, startDate, endDate, userId);
        SavingsGoalDTO createdSavingsGoalDTO = new SavingsGoalDTO(id, goalName, currentAmountOfCash, targetAmountOfCash, startDate, endDate, userId);

        // Mock the SavingsGoalService behavior
        Mockito.when(savingsGoalService.createSavingsGoal(savingsGoalDTO)).thenReturn(createdSavingsGoalDTO);

        // Perform the request to the SavingsGoalController
        ResponseEntity<SavingsGoalDTO> response = savingsGoalController.createSavingsGoal(savingsGoalDTO);

        // Verify the SavingsGoalService method was called
        Mockito.verify(savingsGoalService, Mockito.times(1)).createSavingsGoal(savingsGoalDTO);

        // Verify the response status code and body
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdSavingsGoalDTO, response.getBody());
    }

    @Test
    void getAllSavingsGoals_ShouldReturnListOfSavingsGoalsAndResponseOK() {
        // Prepare test data
        List<SavingsGoalDTO> expectedSavingsGoals = Arrays.asList(
                new SavingsGoalDTO(1, "Goal 1", 1000, 5000, LocalDate.of(2023, 6, 19), LocalDate.of(2023, 9, 10), 1),
                new SavingsGoalDTO(2, "Goal 2", 2000, 8000, LocalDate.of(2023, 7, 1), LocalDate.of(2023, 10, 15), 1)
        );

        // Mock the SavingsGoalService behavior
        Mockito.when(savingsGoalService.getAllSavingsGoals()).thenReturn(expectedSavingsGoals);

        // Perform the request to the SavingsGoalController
        ResponseEntity<List<SavingsGoalDTO>> response = savingsGoalController.getAllSavingsGoals();

        // Verify the SavingsGoalService method was called
        Mockito.verify(savingsGoalService, Mockito.times(1)).getAllSavingsGoals();

        // Verify the response status code and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedSavingsGoals, response.getBody());
    }

    @Test
    void getSavingsGoalById_ShouldReturnSavingsGoalAndResponseOK() {
        // Prepare test data
        int savingsGoalId = 1;
        SavingsGoalDTO expectedSavingsGoal = new SavingsGoalDTO(savingsGoalId, "Goal 1", 1000, 5000, LocalDate.of(2023, 6, 19), LocalDate.of(2023, 9, 10), 1);

        // Mock the SavingsGoalService behavior
        Mockito.when(savingsGoalService.getSavingsGoalById(savingsGoalId)).thenReturn(expectedSavingsGoal);

        // Perform the request to the SavingsGoalController
        ResponseEntity<SavingsGoalDTO> response = savingsGoalController.getSavingsGoalById(savingsGoalId);

        // Verify the SavingsGoalService method was called
        Mockito.verify(savingsGoalService, Mockito.times(1)).getSavingsGoalById(savingsGoalId);

        // Verify the response status code and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedSavingsGoal, response.getBody());
    }

    @Test
    void updateSavingsGoal_ShouldReturnUpdatedSavingsGoalAndResponseOK() {
        // Prepare test data
        int savingsGoalId = 1;
        SavingsGoalDTO savingsGoalDTO = new SavingsGoalDTO(savingsGoalId, "Goal 1", 1000, 5000, LocalDate.of(2023, 6, 19), LocalDate.of(2023, 9, 10), 1);
        SavingsGoalDTO updatedSavingsGoal = new SavingsGoalDTO(savingsGoalId, "Updated Goal", 2000, 8000, LocalDate.of(2023, 6, 19), LocalDate.of(2023, 9, 10), 1);

        // Mock the SavingsGoalService behavior
        Mockito.when(savingsGoalService.updateSavingsGoal(savingsGoalDTO)).thenReturn(updatedSavingsGoal);

        // Perform the request to the SavingsGoalController
        ResponseEntity<SavingsGoalDTO> response = savingsGoalController.updateSavingsGoal(savingsGoalDTO); // Removed redundant id arg (for removed path var)

        // Verify the SavingsGoalService method was called
        Mockito.verify(savingsGoalService, Mockito.times(1)).updateSavingsGoal(savingsGoalDTO);

        // Verify the response status code and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedSavingsGoal, response.getBody());
    }

    @Test
    void deleteSavingsGoal_ShouldReturnResponseNoContent() {
        // Prepare test data
        int savingsGoalId = 1;

        // Perform the request to the SavingsGoalController
        ResponseEntity<Void> response = savingsGoalController.deleteSavingsGoal(savingsGoalId);

        // Verify the SavingsGoalService method was called
        Mockito.verify(savingsGoalService, Mockito.times(1)).deleteSavingsGoal(savingsGoalId);

        // Verify the response status code
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }
}