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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "multimedias")
public class Multimedia implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name = "name", nullable = false)
	private String name;
	
	@NotNull
	@Column(name = "url", nullable = false)
	private String url;
	
	@NotNull
	@Column(name = "type", nullable = false)
	private String type;
	
	@NotNull
	@Column(name = "size", nullable = false)
	private Long size;
	
	@Column(name = "duration")
	private Long duration;
	
	@Column(name = "isEliminated")
	private boolean isEliminated;
	
	@ManyToOne
	@JoinColumn(name = "id_product", referencedColumnName = "id")
	private Product product;
	
	
	

	public Multimedia() {
	}

	public Multimedia(Long id, String name, String url, String type, Long size,
			Long duration, boolean isEliminated) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.type = type;
		this.size = size;
		this.duration = duration;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public boolean isEliminated() {
		return isEliminated;
	}

	public void setEliminated(boolean isEliminated) {
		this.isEliminated = isEliminated;
	}
	
	

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Multimedia [id=" + id + ", name=" + name + ", url=" + url + ", type=" + type + ", size=" + size
				+ ", duration=" + duration + ", isEliminated=" + isEliminated + "]";
	}
	
	
	
}
