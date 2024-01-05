package com.synergisticit.domain;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Customer {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Long customerId;
	
	@NotEmpty
	private String customerName;
	
	@NotEmpty
	private String customerGender;
	
	@NotNull
	//@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private String customerDob;
	
	@NotEmpty
	private String customerMobileNum;
	
	@NotNull
	@Valid
	@Embedded
	private Address customerAddress;
	
	@NotEmpty
	private String realId;
	
	@OneToMany(mappedBy="accountCustomer")
	@JsonManagedReference("account-customer")
	private List<Account> customerAccounts;
	
	@Valid
	@JoinColumn(name="userId")
	@OneToOne
	private User user;
	
}
