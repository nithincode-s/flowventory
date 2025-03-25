package com.example.application.dtos;

import com.example.application.dtos.supplierDto.SupplierCreateDto;
import com.example.application.dtos.supplierDto.SupplierReadDto;
import com.example.application.dtos.supplierDto.SupplierUpdateDto;
import com.example.domain.supplier.Supplier;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface SupplierMapper {
    Supplier toSupplier(SupplierCreateDto incomingSupplier);
    void updateSupplierFromDto(SupplierUpdateDto updateDto, @MappingTarget Supplier supplier);
    SupplierReadDto toSupplierRead(Supplier supplier);
}
