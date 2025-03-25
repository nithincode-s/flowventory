package com.example.application.dtos.supplierDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierCreateDto {
    @Size(min = 2, max = 20, message = "should be between 2 and 20")
    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "Contact details are required")
    private String contactDetails;

    @NotNull(message = "Address is required")
    private String address;

    @NotNull(message = "Email is required")
    @Email
    private String email;
}
