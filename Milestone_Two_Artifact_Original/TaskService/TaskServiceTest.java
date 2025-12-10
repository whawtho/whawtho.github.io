package TaskService;
//Zeb Hawthorne | 6-14-2024 | CS-320 | Module Four | 6-1 Project One

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskServiceTest {
    private TaskService taskService;
    private Task task;

    @BeforeEach
    public void setUp() {
    	taskService = new TaskService();
        task = new Task("TASK12345", "Task Name", "Task Description");
        taskService.addTask(task);
    }
//Success Tests
    @Test
    public void testAddTaskIDSuccess() {
        Task newTask = new Task("TASK67890", "New Task", "New Description");
        taskService.addTask(newTask);
        
        assertTrue(newTask != null);
        assertTrue(newTask.getTaskId().equals("TASK67890"));  
        assertTrue(newTask.getName().equals("New Task"));
        assertTrue(newTask.getDescription().equals("New Description"));
        }
    
    @Test
    public void testUpdateContactDescriptionSuccess() {
        Task newTask = new Task("TASK12345", "New Task Name", "New Description");
        taskService.addTask(newTask);

        taskService.updateTaskDescription("TASK12345", "Updated Description");
        Task updatedTask = taskService.getTask("TASK12345");
        Assertions.assertNotNull(updatedTask);
        Assertions.assertTrue(updatedTask.getDescription().equals("Updated Description"));
    }

    @Test
    public void testGetTaskSuccess() {
        assertEquals(task, taskService.getTask("TASK12345"));
    }

    @Test
    public void testDeleteTaskSuccess() {
        taskService.deleteTask("TASK12345");
        assertNull(taskService.getTask("TASK12345"));
    }
    @Test
    public void testUpdateTaskNameSuccess() {
        Task newTask = new Task("TASK12345", "New Task Name", "New Description");
        taskService.addTask(newTask);

        taskService.updateTaskName("TASK12345", "Updated Task Name");
        Task updatedTask = taskService.getTask("TASK12345");
        Assertions.assertNotNull(updatedTask);
        Assertions.assertTrue(updatedTask.getName().equals("Updated Task Name"));
    }
//Failure Tests
    @Test
    public void testUpdateTaskNameFailure() {
        assertThrows(IllegalArgumentException.class, () -> {
            taskService.updateTaskName("TASK12345", "A very long task name that exceeds the limit");
        });
    }

    @Test
    public void testAddTaskIDFail() {        
        // Create a task with a duplicate ID
        Task dupTask = new Task("TASK12345", "Duplicate Task", "Duplicate Description");

        // Add the task to the service
        taskService.addTask(dupTask);

        // Assert that the original task (with ID "TASK12345") remains unchanged
        Task originalTask = taskService.getTask("TASK12345");
        assertNotNull(originalTask);
        assertEquals("TASK12345", originalTask.getTaskId());  
        assertEquals("Task Name", originalTask.getName());  
        assertEquals("Task Description", originalTask.getDescription());
    }

    @Test
    public void testUpdateTaskDescriptionFailure() {
        assertThrows(IllegalArgumentException.class, () -> {
            taskService.updateTaskDescription("TASK12345", "A very long task description that exceeds the maximum allowed characters limit for this field");
        });
    }

}

