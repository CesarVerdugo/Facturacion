package com.code.facturacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.facturacion.entity.Invoice;
import com.code.facturacion.repository.InvoiceRepository;

@RestController
public class InvoiceController {

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@GetMapping("/facturas")
	public List<Invoice> getInvoices(){
		return invoiceRepository.findAll();
	}
	
}
