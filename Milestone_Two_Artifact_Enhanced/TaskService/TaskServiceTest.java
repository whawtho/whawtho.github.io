package TaskService;
// Zeb Hawthorne | CS-499 | CS-320 Artifact | Task Service Tests (Enhanced)

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskServiceTest {

    private TaskService service;
    private Task task1;
    private Task task2;

    @BeforeEach
    public void setup() {
        service = new TaskService();
        task1 = new Task("T1", "Homework", "Finish milestone two");
        task2 = new Task("T2", "Groceries", "Buy milk and eggs");
        service.addTask(task1);
        service.addTask(task2);
    }

    @Test
    public void testAddTaskSuccess() {
        Task task3 = new Task("T3", "Workout", "Leg day");
        service.addTask(task3);
        assertNotNull(service.getTask("T3"));
    }

    @Test
    public void testAddTaskDuplicateFailure() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.addTask(new Task("T1", "Duplicate", "Should fail"));
        });
    }

    @Test
    public void testDeleteTaskSuccess() {
        service.deleteTask("T1");
        assertNull(service.getTask("T1"));
    }

    // ---------------- ENHANCEMENT TESTS BELOW ----------------

    @Test
    public void testSearchByNameSuccess() { // ENHANCEMENT
        List<Task> results = service.searchByName("home");
        assertEquals(1, results.size());
        assertEquals("T1", results.get(0).getTaskId());
    }

    @Test
    public void testSearchByNameNoMatch() { // ENHANCEMENT
        List<Task> results = service.searchByName("study");
        assertTrue(results.isEmpty());
    }

    @Test
    public void testSearchByDescriptionSuccess() { // ENHANCEMENT
        List<Task> results = service.searchByDescription("milk");
        assertEquals(1, results.size());
        assertEquals("T2", results.get(0).getTaskId());
    }

    @Test
    public void testSearchByDescriptionNoMatch() { // ENHANCEMENT
        List<Task> results = service.searchByDescription("laundry");
        assertTrue(results.isEmpty());
    }
}
