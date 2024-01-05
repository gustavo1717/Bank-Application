package com.synergisticit.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Role {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Long roleId;
	
	@NotEmpty
	private String roleName;
	
	@ManyToMany(mappedBy="roles")
	private List<User> users = new ArrayList<>();
	
	
}
