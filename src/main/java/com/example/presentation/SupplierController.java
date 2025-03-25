package com.example.presentation;

import com.example.application.dtos.supplierDto.SupplierCreateDto;
import com.example.application.dtos.supplierDto.SupplierReadDto;
import com.example.application.dtos.supplierDto.SupplierUpdateDto;
import com.example.application.supplier.ISupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierController {
    @Autowired
    private ISupplierService supplierService;

    @GetMapping
    public ResponseEntity<List<SupplierReadDto>> getAllSuppliers() {
        List<SupplierReadDto> suppliers = supplierService.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    @PostMapping
    public ResponseEntity<SupplierReadDto> createSupplier(@RequestBody @Valid SupplierCreateDto supplier) {
        SupplierReadDto createdSupplier = supplierService.createSupplier(supplier);
        // ResponseEntity.created
        return ResponseEntity.ok(createdSupplier);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierReadDto> getSupplierById(@PathVariable UUID id) {
        SupplierReadDto supplier = supplierService.getSupplierById(id);
        return ResponseEntity.ok(supplier);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierReadDto> updateSupplier(@PathVariable UUID id, @RequestBody @Valid SupplierUpdateDto supplier) {
        SupplierReadDto updatedSupplier = supplierService.updateSupplier(id, supplier);
        return ResponseEntity.ok(updatedSupplier);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable UUID id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }
}
