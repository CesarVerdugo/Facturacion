package com.code.facturacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.facturacion.entity.Multimedia;

@Repository
public interface MultimediaRepository extends JpaRepository<Multimedia, Long>{

}
