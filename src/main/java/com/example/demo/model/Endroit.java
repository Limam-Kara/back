package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "endroits")
public class Endroit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private double latitude;
	private double longitude;

	@ManyToOne
	@JoinColumn(name = "district_id")
	@JsonIgnoreProperties({"endroits", "utilisateurs"}) // Avoid circular reference
	private District district;

	@ManyToOne
	@JoinColumn(name = "annexe_id")
	@JsonIgnoreProperties("endroits")
	private Annexe annexe;

	@ManyToOne
	@JoinColumn(name = "categorie_id")
	@JsonIgnoreProperties("endroits")
	private Categorie categorie;

	@ManyToOne
	@JoinColumn(name = "utilisateur_id")
	@JsonIgnoreProperties({"endroits", "tokens", "password"}) // Avoid circular reference
	private Utilisateur utilisateur;
}
