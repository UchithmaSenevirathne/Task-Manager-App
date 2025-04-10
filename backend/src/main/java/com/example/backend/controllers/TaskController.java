package com.example.backend.controllers;

import com.example.backend.customeObj.TaskResponse;
import com.example.backend.dtos.TaskDTO;
import com.example.backend.exceptions.DataPersistFailedException;
import com.example.backend.exceptions.NotFoundException;
import com.example.backend.services.impl.TaskServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            System.out.println(taskDTO.getStatus());
            taskService.createTask(taskDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistFailedException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update/{taskId}")
    public ResponseEntity<Void> updateTask(@PathVariable("taskId") Long taskId, @RequestBody TaskDTO taskDTO) {
        try {
            taskDTO.setTaskId(taskId);
            System.out.println(taskDTO);
            taskService.updateTask(taskDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/all_tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskDTO> getAllCrops() {
        return taskService.getAllTasks();
    }

    @GetMapping(value = "/get/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskResponse getCropById(@PathVariable("taskId") Long taskId) {
        return taskService.getSelectedTask(taskId);
    }

    @DeleteMapping(value = "/delete/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable("taskId") Long taskId) {
        try {
            taskService.deleteTask(taskId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
