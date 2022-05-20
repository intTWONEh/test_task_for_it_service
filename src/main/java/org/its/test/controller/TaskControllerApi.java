package org.its.test.controller;

import lombok.AllArgsConstructor;
import org.its.test.dto.TaskConditionsDto;
import org.its.test.entity.TaskConditions;
import org.its.test.service.TaskManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class TaskControllerApi {
    private final TaskManager taskManager;

    @PostMapping("answer")
    public String getAnswer(@RequestBody TaskConditionsDto taskConditionsDto) {
        return taskManager.solveTask(taskConditionsDto);
    }

    @GetMapping("tasks")
    public List<String> getNameTasks() {
        return taskManager.getTasksName();
    }

    @PostMapping("save")
    public String saveConditionsTask(@RequestBody TaskConditionsDto taskConditionsDto) {
        return taskManager.save(taskConditionsDto);
    }

    @GetMapping("download/{taskName}")
    public List<TaskConditions> downloadConditionsTask(@PathVariable String taskName) {
        return taskManager.download(taskName);
    }
}