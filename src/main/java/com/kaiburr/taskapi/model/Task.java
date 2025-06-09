package com.kaiburr.taskapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "tasks")
public class Task {

    @Id
    private String id;
    private String name;
    private String owner;
    private String command;
    private String description;


    private List<TaskExecution> executions = new ArrayList<>();

    public Task() {}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public List<TaskExecution> getExecutions() {
        return executions;
    }


    public void setExecutions(List<TaskExecution> executions) {
        this.executions = executions;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
