package com.moe.exception_handling.demo.service;

import com.moe.exception_handling.demo.dto.SupplierDto;

import java.util.List;

public interface SupplierService {
   List<SupplierDto> findAll();
   SupplierDto findById(Long id);
  SupplierDto create(SupplierDto supplierDto);
SupplierDto  update(SupplierDto supplierDto,Long id);
void delete(Long id);
}
