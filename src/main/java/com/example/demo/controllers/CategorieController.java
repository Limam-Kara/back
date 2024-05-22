package com.example.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.Service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.NotFound;
import com.example.demo.model.Categorie;

import com.example.demo.repository.CategorieRepository;


@RestController
@RequestMapping("/SGI/Categorie/")

public class CategorieController {

	@Autowired
	private CategorieService categorieService;

	@GetMapping("/Categories")
	public List<Categorie> getAllCategories() {
		return categorieService.getAllCategories();
	}

	@PostMapping("/Categories")
	public Categorie createCategorie(@RequestBody Categorie categorie) {
		return categorieService.createCategorie(categorie);
	}

	@GetMapping("/Categories/{id}")
	public ResponseEntity<Categorie> getCategorieById(@PathVariable Long id) throws NotFound {
		Categorie categorie = categorieService.getCategorieById(id);
		return ResponseEntity.ok(categorie);
	}

	@PutMapping("/Categories/{id}")
	public ResponseEntity<Categorie> updateCategorie(@PathVariable Long id, @RequestBody Categorie categorieDetails) throws NotFound {
		Categorie updatedCategorie = categorieService.updateCategorie(id, categorieDetails);
		return ResponseEntity.ok(updatedCategorie);
	}

	@DeleteMapping("/Categories/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteCategorie(@PathVariable Long id) throws NotFound {
		categorieService.deleteCategorie(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
