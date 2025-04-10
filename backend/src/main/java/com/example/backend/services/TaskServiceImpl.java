package com.example.backend.services;

import com.example.backend.dtos.TaskDTO;
import com.example.backend.entities.Task;
import com.example.backend.enums.Status;
import com.example.backend.exceptions.NotFoundException;
import com.example.backend.repositories.TaskRepository;
import com.example.backend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Override
    public void updateTask(TaskDTO taskDTO) {
        Optional<Task> tmpTaskEntity = taskRepository.findById(taskDTO.getTaskId());
        System.out.println(tmpTaskEntity);
        if (!tmpTaskEntity.isPresent()) {
            throw new NotFoundException("Task not found");
        }else {
            tmpTaskEntity.get().setTitle(taskDTO.getTitle());
            tmpTaskEntity.get().setDescription(taskDTO.getDescription());
            tmpTaskEntity.get().setStatus(taskDTO.getStatus());
            tmpTaskEntity.get().setCreatedAt(taskDTO.getCreatedAt());
        }
    }
}
