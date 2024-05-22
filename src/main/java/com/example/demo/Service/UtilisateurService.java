package com.example.demo.Service;

import com.example.demo.model.Utilisateur;
import java.util.List;

public interface  UtilisateurService {
    Utilisateur saveUtilisateur(Utilisateur utilisateur);
    Utilisateur getUtilisateurByUsername(String username);

    Utilisateur getUtilisateurById(Integer id);
    List<Utilisateur> getAllUtilisateurs();
    Utilisateur editUtilisateur(Utilisateur utilisateur);
    void deleteUtilisateur(Integer id);
    boolean isUtilisateurExists(Utilisateur utilisateur);
    public Utilisateur getAuthenticatedUser();

}