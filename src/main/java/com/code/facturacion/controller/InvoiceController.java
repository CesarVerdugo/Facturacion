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
import com.code.facturacion.entity.InvoiceDetail;
import com.code.facturacion.entity.Invoice.PaymentStatus;
import com.code.facturacion.repository.InvoiceRepository;
import com.code.facturacion.repository.UserRepository;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public List<Invoice> getInvoices(){
		return invoiceRepository.findAll();
	}
	
	@PostMapping
	public Invoice createInvoiceWithDetails(@Valid @RequestBody Set<InvoiceDetail> details){
		
		Invoice invoice = new Invoice();

		double sumTotalPrice = 0;
		
		for (InvoiceDetail detail : details) {
			sumTotalPrice += detail.getTotalPrice();
			detail.setInvoice(invoice);
		}
		
		invoice.setCustomer(userRepository.findByFirstName("customer").get());
		invoice.setPaymentStatus(PaymentStatus.PENDING);
		invoice.setTotalPrice(sumTotalPrice);	
		invoice.setInvoiceDetails(details);

		return invoiceRepository.save(invoice);
		  
	}
	
}
