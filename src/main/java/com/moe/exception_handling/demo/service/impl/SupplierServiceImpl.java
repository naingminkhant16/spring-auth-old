package com.moe.exception_handling.demo.service.impl;

import com.moe.exception_handling.demo.dto.SupplierDto;
import com.moe.exception_handling.demo.entity.Supplier;
import com.moe.exception_handling.demo.exception.ResourceNotFoundException;
import com.moe.exception_handling.demo.repository.SupplierRepository;
import com.moe.exception_handling.demo.service.SupplierService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final Logger logger = LoggerFactory.getLogger(SupplierServiceImpl.class);

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SupplierDto> findAll() {
        logger.info("Get all suppliers.");
        return supplierRepository
                .findAll()
                .stream()
                .map(s -> modelMapper.map(s, SupplierDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public SupplierDto findById(Long id) {
        logger.info("Get Supplier by id :" + id);
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier", "id", id.toString()));

        return modelMapper.map(supplier, SupplierDto.class);
    }

    @Override
    public SupplierDto create(SupplierDto supplierDto) {

        logger.info("Creating new supplier");
        Supplier supplier = modelMapper.map(supplierDto, Supplier.class);

        Supplier createdSupplier = supplierRepository.save(supplier);
        return modelMapper.map(createdSupplier, SupplierDto.class);
    }

    @Override
    public SupplierDto update(SupplierDto supplierDto, Long id) {

        logger.info("Updating existing supplier");
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier", "id", id.toString()));

        supplier.setName(supplierDto.getName());
        supplier.setAddress(supplierDto.getAddress());
        supplier.setRegistrationNo(supplierDto.getRegistrationNo());
        Supplier updatedSupplier = supplierRepository.save(supplier);

        return modelMapper.map(updatedSupplier, SupplierDto.class);
    }

    @Override
    public void delete(Long id) {
        logger.info("Deleting supplier with id :" + id);
        supplierRepository.deleteById(id);
    }
}
