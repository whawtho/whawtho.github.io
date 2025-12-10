package TaskService;
//Zeb Hawthorne | 6-14-2024 | CS-320 | Module Four | 6-1 Project One


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskTest {
    private Task task;

    @BeforeEach
    public void testTask() {
        task = new Task("TASK12345", "Task Name", "Task Description");
    }

    // Success Tests
    @Test
    public void testGetTaskIdSuccess() {
        assertTrue(task.getTaskId().equals("TASK12345"));
    }

    @Test
    public void testGetNameSuccess() {
        assertTrue(task.getName().equals("Task Name"));
    }

    @Test
    public void testGetDesriptionSuccess() {
        assertTrue(task.getDescription().equals("Task Description"));
    }

    // Failure Tests
    @Test
    public void testContactIdFailure() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("TASK1234567890", "Task Name", "Task Description");
        });
    }

    // Failure Tests
    @Test
    public void testTaskIdFailure() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("TOO_LONG_TASK_ID", "Task Name", "Task Description");
        });
    }

    @Test
    public void testNameFailure() {
        assertThrows(IllegalArgumentException.class, () -> {
            task.setName("A very long task name that exceeds the limit");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            task.setName(null);
        });
    }

    @Test
    public void testDescriptionFailure() {
        assertThrows(IllegalArgumentException.class, () -> {
            task.setDescription("A very long task description that exceeds the maximum allowed characters limit for this field");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            task.setDescription(null);
        });
    }
}
