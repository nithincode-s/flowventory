package com.example.application.supplier;

import com.example.application.dtos.SupplierMapper;
import com.example.application.dtos.supplierDto.SupplierCreateDto;
import com.example.application.dtos.supplierDto.SupplierReadDto;
import com.example.application.dtos.supplierDto.SupplierUpdateDto;
import com.example.domain.supplier.ISupplierRepo;
import com.example.domain.supplier.Supplier;
import com.example.presentation.customException.BadRequest;
import com.example.presentation.customException.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SupplierService implements ISupplierService {
    @Autowired
    private ISupplierRepo supplierRepo;

    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public List<SupplierReadDto> getAllSuppliers() {
        List<Supplier> suppliers = supplierRepo.getAllSuppliers();
        return suppliers.stream()
                .map(supplierMapper::toSupplierRead)
                .collect(Collectors.toList());
    }

    @Override
    public SupplierReadDto createSupplier(SupplierCreateDto supplierDto) {
        Optional<Supplier> existingSupplier = supplierRepo.findByEmail(supplierDto.getEmail());
        if (existingSupplier.isPresent()) {
            throw new BadRequest("Email " + supplierDto.getEmail() + " already exists!");
        }

        Supplier supplier = supplierMapper.toSupplier(supplierDto);
        Supplier savedSupplier = supplierRepo.createSupplier(supplier);
        return supplierMapper.toSupplierRead(savedSupplier);
    }

    @Override
    public SupplierReadDto getSupplierById(UUID id) {
        Supplier supplier = supplierRepo.getSupplierById(id);
        if (supplier == null) {
            throw new ResourceNotFound("Supplier not found with id: " + id);
        }
        return supplierMapper.toSupplierRead(supplier);
    }

    @Override
    public SupplierReadDto updateSupplier(UUID id, SupplierUpdateDto supplierDto) {
        Supplier existingSupplier = supplierRepo.getSupplierById(id);
        if (existingSupplier == null) {
            throw new ResourceNotFound("Supplier not found with id: " + id);
        }
        supplierMapper.updateSupplierFromDto(supplierDto, existingSupplier);
        Supplier savedSupplier = supplierRepo.updateSupplier(existingSupplier);
        return supplierMapper.toSupplierRead(savedSupplier);
    }

    @Override
    public void deleteSupplier(UUID id) {
        Supplier supplier = supplierRepo.getSupplierById(id);
        if (supplier == null) {
            throw new ResourceNotFound("Supplier not found with id: " + id);
        }
        supplierRepo.deleteSupplier(id);
    }
}
