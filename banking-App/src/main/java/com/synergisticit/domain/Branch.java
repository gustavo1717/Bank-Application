package com.synergisticit.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.Date;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Branch {
	
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Long branchId;
	
	@NotEmpty
	private String branchName;
	@Valid
	@Embedded
	private Address branchAddress;
	
	@OneToMany(mappedBy="accountBranch")
	@JsonManagedReference("account-branch")
	private List<Account> branchAccount = new ArrayList<>();

	@Override
	public String toString() {
		return "Branch [branchId=" + branchId + ", branchName=" + branchName + ", branchAddress=" + branchAddress
				+ ", branchAccount=" + branchAccount.size() + "]";
	}
	
	
	
}
