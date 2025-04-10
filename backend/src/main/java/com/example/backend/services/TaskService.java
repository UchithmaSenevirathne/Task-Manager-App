package com.example.backend.services;

import com.example.backend.customeObj.TaskResponse;
import com.example.backend.dtos.TaskDTO;

import java.util.List;

public interface TaskService {
    void createTask(TaskDTO taskDTO);

    void updateTask(TaskDTO taskDTO);

    List<TaskDTO> getAllTasks();

    TaskResponse getSelectedTask(Long taskId);

    void deleteTask(Long taskId);
}
