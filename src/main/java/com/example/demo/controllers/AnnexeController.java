package com.example.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.Service.AnnexeService;
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
import com.example.demo.model.Annexe;
import com.example.demo.repository.AnnexeRepository;

@RestController
@RequestMapping("/SGI/Annexe/")

public class AnnexeController {

	@Autowired
	private AnnexeService annexeService;

	@GetMapping("/Annexes")
	public List<Annexe> getAllAnnexes() {
		return annexeService.getAllAnnexes();
	}

	@PostMapping("/Annexes")
	public Annexe createAnnexe(@RequestBody Annexe annexe) {
		return annexeService.createAnnexe(annexe);
	}

	@GetMapping("/Annexes/{id}")
	public ResponseEntity<Annexe> getAnnexeById(@PathVariable Long id) throws NotFound {
		Annexe annexe = annexeService.getAnnexeById(id);
		return ResponseEntity.ok(annexe);
	}

	@PutMapping("/Annexes/{id}")
	public ResponseEntity<Annexe> updateAnnexe(@PathVariable Long id, @RequestBody Annexe annexeDetails) throws NotFound {
		Annexe updatedAnnexe = annexeService.updateAnnexe(id, annexeDetails);
		return ResponseEntity.ok(updatedAnnexe);
	}

	@DeleteMapping("/Annexes/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteAnnexe(@PathVariable Long id) throws NotFound {
		annexeService.deleteAnnexe(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}

