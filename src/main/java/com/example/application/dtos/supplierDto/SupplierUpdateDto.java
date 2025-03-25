package com.example.application.dtos.supplierDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class SupplierUpdateDto {
    @Size(min = 2, max = 20, message = "should be between 2 and 20")
    private String name;
    private String contactDetails;
    private String address;
    @Email
    private String email;
}
