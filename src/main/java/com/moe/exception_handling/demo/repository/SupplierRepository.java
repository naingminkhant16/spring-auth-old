package com.moe.exception_handling.demo.repository;

import com.moe.exception_handling.demo.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier,Long> {
}
