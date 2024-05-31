package com.example.springboot.user.dtos;

import com.example.springboot.user.enums.UserRole;
import jakarta.validation.constraints.NotNull;

public record RegisterDto(@NotNull String email, @NotNull String password, @NotNull UserRole role ) {

}