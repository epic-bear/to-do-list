package com.app.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "actions")
public class Action extends PanacheEntity {

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 30, message = "Description cannot be longer than 30 characters")
    private String description;

    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @ManyToOne
    @NotNull
    private User user;
}
