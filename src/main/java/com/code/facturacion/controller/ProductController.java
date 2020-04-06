package com.code.facturacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.facturacion.entity.Product;
import com.code.facturacion.repository.ProductRepository;

@RestController
public class ProductController{
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/productos")
	public List<Product> geProducts(){
		return productRepository.findAll();
	}

}
