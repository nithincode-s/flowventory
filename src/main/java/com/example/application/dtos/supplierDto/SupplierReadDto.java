package com.example.application.dtos.supplierDto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SupplierReadDto {
    private UUID id;
    private String name;
    private String contactDetails;
    private String address;
    private String email;
}
