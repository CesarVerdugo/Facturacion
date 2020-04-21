package com.code.facturacion.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "products")
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 4, max = 30)
	@Column(name = "name", nullable = false)
	private String name;
	
	@NotNull
	@Column(name = "prece")
	private double price;
	
	@Column(name = "min_stock")
	private int minStock;
	
	@Column(name = "stock")
	private int stock;
	
	@NotNull
	@Column(name = "is_eliminated", nullable = false)
	private boolean isEliminated;
	
	@ManyToOne
	@JoinColumn(name = "id_category", referencedColumnName = "id")
	private Category category;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private Set<InvoiceDetail> invoiceDetails;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private Set<Multimedia> multimedias;
	
	public Product() {
	}

	public Product(Long id, String name, double price, int minStock, int stock, boolean isEliminated) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.minStock = minStock;
		this.stock = stock;
		this.isEliminated = isEliminated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getMinStock() {
		return minStock;
	}

	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public boolean isEliminated() {
		return isEliminated;
	}

	public void setEliminated(boolean isEliminated) {
		this.isEliminated = isEliminated;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", minStock=" + minStock + ", stock="
				+ stock + ", isEliminated=" + isEliminated + "]";
	}
	
	
	
	
}
