package com.code.facturacion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.facturacion.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByFirstName(String firstName);
	Optional<User> findOneByDocumentNumber(String documentNumber);
	Optional<User> findOneByEmailIgnoreCase(String email);
	
}
