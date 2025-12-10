package TaskService;
//Zeb Hawthorne | 6-14-2024 | CS-320 | Module Four | 6-1 Project One

import java.util.HashMap;
import java.util.Map;

public class TaskService {
	private final Map<String, Task> tasks;

    public TaskService() {
        this.tasks = new HashMap<>();
    }

    public void addTask(Task task) {
        if (task != null && !tasks.containsKey(task.getTaskId())) {
            tasks.put(task.getTaskId(), task);
        }
    }

    public Task getTask(String taskId) {
        return tasks.get(taskId);
    }

    public void deleteTask(String taskId) {
        tasks.remove(taskId);
    }
    public void updateTaskName(String taskId, String name) {
        Task task = tasks.get(taskId);
        if (task != null) {
            task.setName(name);
        }
    }

    public void updateTaskDescription(String taskId, String description) {
        Task task = tasks.get(taskId);
        if (task != null) {
            task.setDescription(description);
        }
    }
}
