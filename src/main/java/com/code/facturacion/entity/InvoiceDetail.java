package com.code.facturacion.entity;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "invoice_details")
public class InvoiceDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Min(value = 1)
    @Column(name = "quantity", nullable = false)
	private int quantity;
	
	@NotNull
	@Column(name = "total_price" , nullable = false) 
	private double totalPrice;
	
	@NotNull
	@Column(name = "is_eliminated", nullable = false ) 
	private boolean isEliminated;
	
	@ManyToOne
	@JoinColumn(name = "id_product", referencedColumnName = "id" )
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "id_invoice",referencedColumnName = "id")
	private Invoice invoice;
	
	public InvoiceDetail() {
	}

	public InvoiceDetail(Long id, int quantity, double totalPrice, boolean isEliminated) {
		this.id = id;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.isEliminated = isEliminated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	@Override
	public String toString() {
		return "InvoiceDetail [id=" + id + ", quantity=" + quantity + ", totalPrice=" + totalPrice + ", isEliminated="
				+ isEliminated + "]";
	}
	
	
	
	
}
