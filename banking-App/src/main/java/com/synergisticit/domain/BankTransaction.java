package com.synergisticit.domain;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class BankTransaction {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long bankTransactionId;
	
	@JsonBackReference(value="source-transactions")
	@ManyToOne
	@JoinColumn(name = "toAccountId", nullable = true)
	private Account bankTransactionToAccount;

	@JsonBackReference(value="destination-transactions")
	@ManyToOne
	@JoinColumn(name = "fromAccountId", nullable = true)
	private Account bankTransactionFromAccount;
	
	@DecimalMin(value = "0.01")
	private double transactionAmount;
	
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime bankTransactionDateTime;
	
	private String comments;
	
}
