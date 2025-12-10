package TaskService;
//Zeb Hawthorne | CS-499 | CS-320 Artifact | Task Unit Tests

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void testTaskCreationSuccess() {
        Task t = new Task("T1", "Homework", "Finish CS-499 milestone");
        assertEquals("T1", t.getTaskId());
        assertEquals("Homework", t.getName());
        assertEquals("Finish CS-499 milestone", t.getDescription());
    }

    @Test
    public void testTaskIdFailureTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("T12345678901", "Homework", "Desc");
        });
    }

    @Test
    public void testNameFailureNullOrTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("T1", null, "Desc");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Task("T1", "ThisNameIsWayTooLongToBeValid", "Desc");
        });
    }

    @Test
    public void testDescriptionFailureNullOrTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("T1", "Homework", null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Task("T1", "Homework",
                "This description is definitely too long to be accepted because it exceeds fifty characters");
        });
    }
}
