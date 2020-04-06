package com.code.facturacion.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "categories")
public class Category implements Serializable  {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 4, max = 20)
	@Column(name = "name", nullable = false)
	private String name;
	
	@NotNull
	@Column(name = "is_eliminated", nullable = false)
	private boolean isEliminated;
	
	@OneToMany(mappedBy = "category")
	private Set<Product> products;
	
	public Category() {
	}

	public Category(Long id, String name, boolean isEliminated) {
		this.id = id;
		this.name = name;
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

	
	public boolean isEliminated() {
		return isEliminated;
	}

	
	public void setEliminated(boolean isEliminated) {
		this.isEliminated = isEliminated;
	}
	
	
}
