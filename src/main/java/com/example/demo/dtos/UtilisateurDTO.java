package com.example.demo.dtos;

import com.example.demo.model.Utilisateur;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UtilisateurDTO {
    private Integer id;
    private String ppr;
    private String nom;
    private String prenom;
    private String fonction;
    private String username;
    private String email;
    private String tel;
    private String genre;
    private String adresse;
    private Date dateDeNaissance;
    private Integer role;

    // Default constructor
    public UtilisateurDTO() {}

    // Constructor to initialize fields
    public UtilisateurDTO(Integer id, String ppr, String nom, String prenom, String fonction, String username, String email, String tel, String genre, String adresse, Date dateDeNaissance, Integer role) {
        this.id = id;
        this.ppr = ppr;
        this.nom = nom;
        this.prenom = prenom;
        this.fonction = fonction;
        this.username = username;
        this.email = email;
        this.tel = tel;
        this.genre = genre;
        this.adresse = adresse;
        this.dateDeNaissance = dateDeNaissance;
        this.role = role;
    }
    public UtilisateurDTO toDto(Utilisateur utilisateur) {
        return new UtilisateurDTO(
                utilisateur.getId(),
                utilisateur.getPpr(),
                utilisateur.getNom(),
                utilisateur.getPrenom(),
                utilisateur.getFonction(),
                utilisateur.getUsername(),
                utilisateur.getEmail(),
                utilisateur.getTel(),
                utilisateur.getGenre(),
                utilisateur.getAdresse(),
                utilisateur.getDateDeNaissance(),
                utilisateur.getIdRole().getId()
        );
    }

}
