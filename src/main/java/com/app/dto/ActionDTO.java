package com.app.dto;


import com.app.domain.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ActionDTO {

    private Long actionId;

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 30, message = "Description cannot be longer than 30 characters")
    private String description;

    private String username;

    private String createdDate;

    private String status;
}
