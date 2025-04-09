package com.example.backend.dtos;

import com.example.backend.enums.Status;
import lombok.Data;


import java.time.LocalDateTime;

@Data
public class TaskDTO {
    private Long taskId;
    private String title;
    private String description;
    private Status status;
    private LocalDateTime createdAt;
    private Long userId;
}
