package com.example.backend.entities;

import com.example.backend.dtos.TaskDTO;
import com.example.backend.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Data
public class Task{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    private String title;
    private String description;
    private Status status;
    private LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public TaskDTO getTaskDTO(){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskId(taskId);
        taskDTO.setTitle(title);
        taskDTO.setDescription(description);
        taskDTO.setStatus(status);
        taskDTO.setCreatedAt(createdAt);
        taskDTO.setUserId(user.getId());
        return taskDTO;
    }
}
