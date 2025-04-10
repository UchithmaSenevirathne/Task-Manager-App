package com.example.backend.services;

import com.example.backend.dtos.TaskDTO;

public interface TaskService {
    void createTask(TaskDTO taskDTO);

    void updateTask(TaskDTO taskDTO);
}
