package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Categorie;
@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long>{

}
