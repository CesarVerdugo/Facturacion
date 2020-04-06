package com.code.facturacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.facturacion.entity.InvoiceDetail;
import com.code.facturacion.repository.InvoiceDetailRepository;

@RestController
public class InvoiceDetailsController {

	@Autowired
	private InvoiceDetailRepository invoiceDetailRepository;
	
	@GetMapping("/detallefacturas")
	public List<InvoiceDetail> getInvoiceDetails(){
		return invoiceDetailRepository.findAll();
	}
}
