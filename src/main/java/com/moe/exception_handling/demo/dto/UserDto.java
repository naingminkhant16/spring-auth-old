package com.moe.exception_handling.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    @NotNull(message = "Username is required.")
    private String username;
    @NotNull(message = "Password is required.")
    private String password;
}
