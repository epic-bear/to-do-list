package com.app.dto;


import com.app.domain.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ActionDTO {

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 30, message = "Description cannot be longer than 30 characters")
    private String description;

    @NotBlank
    private String username;

    private LocalDateTime createdDate;

    private Status status = Status.NEW;
}
