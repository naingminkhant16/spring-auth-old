package com.moe.exception_handling.demo.entity;

import com.moe.exception_handling.demo.dto.SupplierDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "suppliers")
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "reg_no")
    private int registrationNo;

    public SupplierDto viewAsOutputDto() {
        return new SupplierDto(id, name, address, registrationNo);
    }
}
