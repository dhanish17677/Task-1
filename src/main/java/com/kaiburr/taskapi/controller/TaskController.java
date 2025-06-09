package com.kaiburr.taskapi.controller;

import com.kaiburr.taskapi.model.Task;
import com.kaiburr.taskapi.model.TaskExecution;
import com.kaiburr.taskapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(required = false) String id) {
        if (id != null) {
            Task task = taskService.getTaskById(id);
            return ResponseEntity.ok(task);
        }
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task savedTask = taskService.createTask(task);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable String id, @RequestBody Task updatedTask) {
        Task updated = taskService.updateTask(id, updatedTask);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/execution")
    public ResponseEntity<Task> executeTask(@PathVariable String id) {
        Task executedTask = taskService.executeTask(id);
        return ResponseEntity.ok(executedTask);
    }


    @GetMapping("/{id}/executions")
    public ResponseEntity<List<TaskExecution>> getTaskExecutions(@PathVariable String id) {
        List<TaskExecution> executions = taskService.getExecutionsForTask(id);
        return ResponseEntity.ok(executions);
    }
}
