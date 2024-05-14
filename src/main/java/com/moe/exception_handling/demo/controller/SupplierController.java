package com.moe.exception_handling.demo.controller;


import com.moe.exception_handling.demo.dto.SupplierDto;
import com.moe.exception_handling.demo.service.SupplierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@Validated
@Tag(name = "Supplier Operation", description = "CRUD operation for Supplier entity.")
public class SupplierController {
    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("")
    @Operation(summary = "Get all Supplier")
    public ResponseEntity<List<SupplierDto>> getAll() {
        List<SupplierDto> supplierDtoList = supplierService.findAll();
        return new ResponseEntity<>(supplierDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Supplier by ID")
    public ResponseEntity<SupplierDto> getById(@PathVariable Long id) {
        SupplierDto supplierDto = supplierService.findById(id);
        return new ResponseEntity<>(supplierDto, HttpStatus.OK);
    }

    @PostMapping("")
    @Operation(summary = "Create new Supplier")
    public ResponseEntity<SupplierDto> create(@Valid @RequestBody SupplierDto supplierDto) {
        SupplierDto createdSupplierDto = supplierService.create(supplierDto);
        return new ResponseEntity<>(createdSupplierDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update existing Supplier with ID")
    public ResponseEntity<SupplierDto> update(@Valid @RequestBody SupplierDto supplierDto, @PathVariable Long id) {
        SupplierDto updatedSupplierDto = supplierService.update(supplierDto, id);
        return new ResponseEntity<>(updatedSupplierDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Supplier by ID")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        supplierService.delete(id);
        return new ResponseEntity<>("Successfully deleted!", HttpStatus.NO_CONTENT);
    }
}
