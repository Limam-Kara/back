package com.example.demo.Service;

import com.example.demo.model.Utilisateur;

public interface TokenService {
    public void saveToken(String jwt, Utilisateur user);
    public void revokeAllTokensByUser(Utilisateur user);
}