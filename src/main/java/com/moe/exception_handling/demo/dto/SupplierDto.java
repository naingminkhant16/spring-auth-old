package com.moe.exception_handling.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SupplierDto {

    private Long id;

    @NotNull(message = "Supplier name is required.")
    @Size(min = 4, max = 30, message = "Invalid Name : Must be of 4 -30 characters.")
    private String name;

    @NotNull(message = "Address is required.")
    private String address;

    @NotNull(message = "Registration number is required")
    private int registrationNo;
}
