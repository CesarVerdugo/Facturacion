package com.code.facturacion.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "invoices")
public class Invoice implements Serializable {
    
	
	public enum PaymentStatus {
		PENDING,
		CANCELLED;
	}
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name = "total_price", nullable = false)
	private double totalPrice;
	
	@NotNull
	@Column(name = "is_eliminated", nullable = false)
	private boolean isEliminated;
	
	@Column(name = "amount_owed")
	private double amountOwed;
	
	@Column(name = "amount_paid")
	private double amountPaid;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "payment_status")
	private PaymentStatus paymentStatus;
	
	@JsonIgnore
	@OneToMany(mappedBy = "invoice", cascade=CascadeType.PERSIST)
	private Set<InvoiceDetail> invoiceDetails;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_customer", referencedColumnName = "id")
	private User customer;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_cashier", referencedColumnName = "id")
	private User cashier;
	
	


	public Invoice() {
	}


	public Invoice(Long id, double totalPrice, boolean isEliminated, double amountOwed, double amountPaid) {
		this.id = id;
		this.totalPrice = totalPrice;
		this.isEliminated = isEliminated;
		this.amountOwed = amountOwed;
		this.amountPaid = amountPaid;
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public double getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}


	public boolean isEliminated() {
		return isEliminated;
	}


	public void setEliminated(boolean isEliminated) {
		this.isEliminated = isEliminated;
	}


	public double getAmountOwed() {
		return amountOwed;
	}


	public void setAmountOwed(double amountOwed) {
		this.amountOwed = amountOwed;
	}


	public double getAmountPaid() {
		return amountPaid;
	}


	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}


	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}


	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	

	public Set<InvoiceDetail> getInvoiceDetails() {
		return invoiceDetails;
	}


	public void setInvoiceDetails(Set<InvoiceDetail> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}


	public User getCustomer() {
		return customer;
	}


	public void setCustomer(User customer) {
		this.customer = customer;
	}


	public User getCashier() {
		return cashier;
	}


	public void setCashier(User cashier) {
		this.cashier = cashier;
	}


	@Override
	public String toString() {
		return "Invoice [id=" + id + ", totalPrice=" + totalPrice + ", isEliminated=" + isEliminated + ", amountOwed="
				+ amountOwed + ", amountPaid=" + amountPaid + ", paymentStatus=" + paymentStatus + "]";
	}

}
