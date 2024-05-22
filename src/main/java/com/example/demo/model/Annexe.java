package com.example.demo.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "annexes")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Annexe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String adresse;

    @ManyToOne
    @JoinColumn(name = "district_id")
    @JsonIgnoreProperties({"annexes", "utilisateurs"})
    private District district;

    @OneToMany(mappedBy = "annexe", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"annexe", "utilisateur", "categorie", "district"})
    private List<Endroit> endroits;
}
