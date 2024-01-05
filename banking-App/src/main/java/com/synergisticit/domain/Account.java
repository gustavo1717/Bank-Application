package com.synergisticit.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Long accountId;
	
	@NotEmpty
	private String accountHolder;
	
	@Enumerated(EnumType.STRING)
	private AccountType accountType; 
	
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate accountDateOpen;
	
	private double accountBalance;
	
	@JoinColumn(name="branchId")
	@JsonBackReference(value="account-branch")
	@ManyToOne
	private Branch accountBranch;
	
	@JoinColumn(name="customerId")
	@JsonBackReference(value="account-customer")
	@ManyToOne
	private Customer accountCustomer;
	
	@JsonManagedReference(value="source-transactions")
	@OneToMany(mappedBy = "bankTransactionFromAccount",  orphanRemoval = false)
    private List<BankTransaction> sourceTransactions = new ArrayList<>();

	@JsonManagedReference(value="destination-transactions")
    @OneToMany(mappedBy = "bankTransactionToAccount", orphanRemoval = false)
    private List<BankTransaction> destinationTransactions = new ArrayList<>();

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountHolder=" + accountHolder + ", accountType=" + accountType
				+ ", accountDateOpen=" + accountDateOpen==null?"null":accountDateOpen + ", accountBalance=" + accountBalance + ", accountBranch="
				+ accountBranch!=null?accountBranch.getBranchName():"Deleted" + ", accountCustomer=" + accountCustomer!=null?accountCustomer.getCustomerName():"Deleted" + "]";
	}
	
	
	
}
