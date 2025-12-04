package com.taskmanager.model.entity;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

import com.taskmanager.model.Role;

@Builder
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "username")
    private String username;

    @NotBlank
    @Email
    @Column(name = "email")
    private String email;

    @NotBlank
    @Size(min = 8, max = 100)
    @Column(name = "password")
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @NotNull
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @NotNull
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
