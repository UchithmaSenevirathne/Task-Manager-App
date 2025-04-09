package com.example.backend.services;

import com.example.backend.dtos.TaskDTO;
import com.example.backend.repositories.TaskRepository;
import com.example.backend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private Mapping mapping;

    @Override
    public void createTask(TaskDTO taskDTO) {
        taskRepository.save(mapping.convertToTaskEntity(taskDTO));
    }
}
