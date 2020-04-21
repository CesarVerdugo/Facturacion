package com.code.facturacion.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 3, max = 50)
	@Column(name = "first_name", length = 50)
	private String firstName;
	
	@Size(min = 3, max = 50)
	@Column(name = "last_name", length = 50)
	private String lastName;
	
	@Email
	@Size(min = 5, max = 254)
	@Column(name = "email", length = 254, unique = true)
	private String email;
	
	@Size(min = 3, max = 30)
	@Column(name = "password")
	private String password;
	
	@Column(name = "document_number")
	private String documentNumber;
	
	@Size(min = 3, max = 50)
	@Column(name = "phone", length = 50)
	private String phone;
	
	@NotNull
	@Column(name = "is_active",nullable = false)
	private boolean isActive;
	
	@NotNull
	@Column(name = "is_eliminated", nullable = false)
	private boolean isEliminated;
	
	
	
	@JoinTable(
			name = "users_has_roles",
			joinColumns={@JoinColumn(name = "id_users",referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "id_roles",referencedColumnName = "id")})
	@ManyToMany()
	private Set<Role> roles;
	
	@OneToMany(mappedBy = "customer")
	private Set<Invoice> invoicesPay;

	@OneToMany(mappedBy = "cashier")
	private Set<Invoice> invoices;
	
	public User() {	}
	
	

	public User(Long id, String firstName, String lastName, String email,String password,String documentNumber, String phone,
			boolean isActive, boolean isEliminated) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.documentNumber = documentNumber;
		this.phone = phone;
		this.isActive = isActive;
		this.isEliminated = isEliminated;
	}



	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public String getFirstName() {
		return firstName;
	}

	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	
	public String getLastName() {
		return lastName;
	}

	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	
	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getPhone() {
		return phone;
	}

	
	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	public boolean isActive() {
		return isActive;
	}

	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	
	public boolean isEliminated() {
		return isEliminated;
	}

	
	public void setEliminated(boolean isEliminated) {
		this.isEliminated = isEliminated;
	}

	
	public Set<Invoice> getInvoicesPay() {
		return invoicesPay;
	}



	public void setInvoicesPay(Set<Invoice> invoicesPay) {
		this.invoicesPay = invoicesPay;
	}



	public Set<Invoice> getInvoices() {
		return invoices;
	}



	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}
	
	



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getDocumentNumber() {
		return documentNumber;
	}



	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", documentNumber=" + documentNumber + ", phone=" + phone + ", isActive="
				+ isActive + ", isEliminated=" + isEliminated + "]";
	}

	
	    	
}
