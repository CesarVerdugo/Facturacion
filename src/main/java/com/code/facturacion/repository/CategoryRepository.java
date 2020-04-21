package com.code.facturacion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.facturacion.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	Optional<Category> findByName(String name);
}
