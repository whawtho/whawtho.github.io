package TaskService;
//Zeb Hawthorne | CS-499 | CS-320 Artifact | Task Service (Enhanced Search)

import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private final List<Task> tasks;

    public TaskService() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }

        for (Task t : tasks) {
            if (t.getTaskId().equals(task.getTaskId())) {
                throw new IllegalArgumentException("Duplicate task ID");
            }
        }

        tasks.add(task);
    }

    public void deleteTask(String taskId) {
        tasks.removeIf(t -> t.getTaskId().equals(taskId));
    }

    public Task getTask(String taskId) {
        for (Task t : tasks) {
            if (t.getTaskId().equals(taskId)) {
                return t;
            }
        }
        return null;
    }

    public void updateTaskName(String taskId, String name) {
        Task task = getTask(taskId);
        if (task != null) {
            task.setName(name);
        }
    }

    public void updateTaskDescription(String taskId, String description) {
        Task task = getTask(taskId);
        if (task != null) {
            task.setDescription(description);
        }
    }

    // ---------------- ENHANCEMENTS BELOW ----------------

    // ENHANCEMENT: search tasks by name (case-insensitive, partial match)
    public List<Task> searchByName(String query) {
        List<Task> results = new ArrayList<>();

        if (query == null || query.trim().isEmpty()) {
            return results;
        }

        String q = query.toLowerCase();
        for (Task t : tasks) {
            if (t.getName().toLowerCase().contains(q)) {
                results.add(t);
            }
        }
        return results;
    }

    // ENHANCEMENT: search tasks by description (case-insensitive, partial match)
    public List<Task> searchByDescription(String query) {
        List<Task> results = new ArrayList<>();

        if (query == null || query.trim().isEmpty()) {
            return results;
        }

        String q = query.toLowerCase();
        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(q)) {
                results.add(t);
            }
        }
        return results;
    }
}
