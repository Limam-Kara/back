package com.example.demo.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "districts")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class District {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;

	@OneToMany(mappedBy = "district",  orphanRemoval = true)
	@JsonIgnoreProperties("district")
	private List<Annexe> annexes;

	@OneToMany(mappedBy = "district",  orphanRemoval = true)
	@JsonIgnoreProperties("district")
	private List<Utilisateur> utilisateurs;
}
