package com.code.facturacion.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.facturacion.entity.Invoice;
import com.code.facturacion.entity.Invoice.PaymentStatus;
import com.code.facturacion.entity.InvoiceDetail;
import com.code.facturacion.repository.InvoiceDetailRepository;
import com.code.facturacion.repository.InvoiceRepository;
import com.code.facturacion.repository.UserRepository;

@RestController
@RequestMapping("/invoice-details")
public class InvoiceDetailsController {

	@Autowired
	private InvoiceDetailRepository invoiceDetailRepository;
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public List<InvoiceDetail> getInvoiceDetails(){
		return invoiceDetailRepository.findAll();
	}
	
	
}
