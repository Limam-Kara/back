package com.example.demo.Service;

import com.example.demo.exception.NotFound;
import com.example.demo.model.Categorie;

import java.util.List;

public interface CategorieService {
    List<Categorie> getAllCategories();
    Categorie createCategorie(Categorie categorie);
    Categorie getCategorieById(Long id) throws NotFound;
    Categorie updateCategorie(Long id, Categorie categorieDetails) throws NotFound;
    void deleteCategorie(Long id) throws NotFound;
}
