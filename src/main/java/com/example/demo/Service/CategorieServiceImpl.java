package com.example.demo.Service;

import com.example.demo.exception.NotFound;
import com.example.demo.model.Categorie;
import com.example.demo.repository.CategorieRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieServiceImpl implements CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    @Override
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    @Override
    public Categorie createCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public Categorie getCategorieById(Long id) throws NotFound {
        return categorieRepository.findById(id)
                .orElseThrow(() -> new NotFound("Categorie not exist with id: " + id));
    }

    @Override
    public Categorie updateCategorie(Long id, Categorie categorieDetails) throws NotFound {
        Categorie categorie = getCategorieById(id);

        categorie.setNom(categorieDetails.getNom());
        categorie.setEndroits(categorieDetails.getEndroits());

        return categorieRepository.save(categorie);
    }

    @Override
    public void deleteCategorie(Long id) throws NotFound {
        Categorie categorie = getCategorieById(id);
        categorieRepository.delete(categorie);
    }
}
