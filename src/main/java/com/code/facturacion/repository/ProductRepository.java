package com.code.facturacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.facturacion.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
