package com.example.infrastructure.supplier;

import com.example.application.dtos.SupplierMapper;
import com.example.domain.supplier.ISupplierRepo;
import com.example.domain.supplier.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class SupplierRepo implements ISupplierRepo {
    @Autowired
    private ISupplierJpaRepo supplierJpaRepo;

    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierJpaRepo.findAll();
    }

    @Override
    public Supplier createSupplier(Supplier supplier) {
        return supplierJpaRepo.save(supplier);
    }

    @Override
    public Supplier getSupplierById(UUID id) {
        return supplierJpaRepo.findById(id).orElse(null);
    }

    @Override
    public Supplier updateSupplier(Supplier supplier) {
        return supplierJpaRepo.save(supplier);
    }

    @Override
    public void deleteSupplier(UUID id) {
        supplierJpaRepo.deleteById(id);
    }

    @Override
    public Optional<Supplier> findByEmail(String email) {
        return supplierJpaRepo.findByEmail(email);
    }
}
