package com.example.backend.controllers;

import com.example.backend.dtos.TaskDTO;
import com.example.backend.entities.Task;
import com.example.backend.exceptions.DataPersistFailedException;
import com.example.backend.services.TaskServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/backend/task")
@CrossOrigin
public class TaskController {
    private final TaskServiceImpl taskService;

    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody TaskDTO taskDTO) {
        try {
            taskService.createTask(taskDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistFailedException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
