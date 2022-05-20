package org.its.test.service;

import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.its.test.dto.MapperDto;
import org.its.test.dto.TaskConditionsDto;
import org.its.test.entity.TaskConditions;
import org.its.test.model.Task;
import org.its.test.repository.TaskConditionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class TaskManager {
    private final TaskConditionsRepository taskConditionsRepository;
    private final Map<String, Task> taskList;

    public TaskManager(TaskConditionsRepository taskConditionsRepository, Map<String, Task> taskList) {
        this.taskConditionsRepository = taskConditionsRepository;
        this.taskList = new CaseInsensitiveMap<>();
        this.taskList.putAll(taskList);
    }

    public String save(final TaskConditionsDto taskConditionsDto){
        Task task = taskList.get(taskConditionsDto.getTaskName());

        if(task == null) {
            return "The system is not busy with such tasks.";
        }

        if(task.checkConditions(taskConditionsDto.getConditions())) {
            return "Check if the condition is entered correctly.";
        }

        taskConditionsRepository.save(MapperDto.getTaskConditions(taskConditionsDto));

        return "Conditions for the task " + taskConditionsDto.getTaskName() + " saved.";
    }

    public List<TaskConditions> download(final String taskName) {
        return taskConditionsRepository.findByTaskName(taskName);
    }

    public String solveTask(final TaskConditionsDto conditions) {
        if(conditions == null) {
            return null;
        }

        if(conditions.getTaskName() == null || conditions.getConditions() == null) {
            return "Check the fields.";
        }

        return taskList.get(conditions.getTaskName())
                .getResult(conditions.getConditions());
    }

    public List<String> getTasksName() {
        return taskList.values().stream()
                .map(Task::getName)
                .collect(Collectors.toList());
    }
}