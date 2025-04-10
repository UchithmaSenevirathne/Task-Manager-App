package com.example.backend.services.impl;

import com.example.backend.customeObj.TaskResponse;
import com.example.backend.dtos.TaskDTO;
import com.example.backend.entities.Task;
import com.example.backend.exceptions.NotFoundException;
import com.example.backend.repositories.TaskRepository;
import com.example.backend.services.TaskService;
import com.example.backend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
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

    @Override
    public List<TaskDTO> getAllTasks() {
        return mapping.convertToTaskDTOList(taskRepository.findAll());
    }

    @Override
    public TaskResponse getSelectedTask(Long taskId) {
        if (taskRepository.existsById(taskId)) {
            return mapping.convertToTaskDTO(taskRepository.getReferenceById(taskId));
        }else{
            throw new NotFoundException("Task not found");
        }
    }

    @Override
    public void deleteTask(Long taskId) {
        Optional<Task> findId = taskRepository.findById(taskId);
        if(!findId.isPresent()){
            throw new NotFoundException("Task not found");
        }else {
            taskRepository.deleteById(taskId);
        }
    }
}
