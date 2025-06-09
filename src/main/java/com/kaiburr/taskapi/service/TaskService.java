package com.kaiburr.taskapi.service;

import com.kaiburr.taskapi.model.Task;
import com.kaiburr.taskapi.model.TaskExecution;
import com.kaiburr.taskapi.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        Date now = new Date();
        TaskExecution execution = new TaskExecution(now, now, "pending");

        if (task.getExecutions() == null) {
            task.setExecutions(new ArrayList<>());
        }

        task.getExecutions().add(execution);

        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(String id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found: " + id));
    }

    public Task updateTask(String id, Task updatedTask) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        if (updatedTask.getName() != null) {
            existingTask.setName(updatedTask.getName());
        }
        if (updatedTask.getDescription() != null) {
            existingTask.setDescription(updatedTask.getDescription());
        }
        if (updatedTask.getOwner() != null) {
            existingTask.setOwner(updatedTask.getOwner());
        }
        if (updatedTask.getCommand() != null) {
            existingTask.setCommand(updatedTask.getCommand());
        }

        if (updatedTask.getExecutions() != null && !updatedTask.getExecutions().isEmpty()) {
            List<TaskExecution> existingExecutions = existingTask.getExecutions();
            if (existingExecutions == null) {
                existingExecutions = new ArrayList<>();
                existingTask.setExecutions(existingExecutions);
            }
            existingExecutions.addAll(updatedTask.getExecutions());
        }

        return taskRepository.save(existingTask);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }

    public Task executeTask(String id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        Date now = new Date();
        TaskExecution execution = new TaskExecution(now, now, "executed");

        if (task.getExecutions() == null) {
            task.setExecutions(new ArrayList<>());
        }

        task.getExecutions().add(execution);

        return taskRepository.save(task);
    }

    // ✅ NEW METHOD for getting executions
    public List<TaskExecution> getExecutionsForTask(String taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));
        return task.getExecutions();
    }
}
