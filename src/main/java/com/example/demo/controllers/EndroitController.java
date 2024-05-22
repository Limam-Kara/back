package com.example.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.Service.EndroitService;
import com.example.demo.exception.DuplicateEndroitException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.example.demo.model.Endroit;
import com.example.demo.repository.EndroitRepository;

@RestController
@RequestMapping("/SGI/Endroit/")

public class EndroitController {

	@Autowired
	private EndroitService endroitService;

	@GetMapping("/Endroits")
	public List<Endroit> getAllEndroits() {
		return endroitService.getAllEndroits();
	}

	@PostMapping("/Endroits")
	public ResponseEntity<Endroit> createEndroit(@RequestBody Endroit endroit) {
		try {
			Endroit createdEndroit = endroitService.createEndroit(endroit);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdEndroit);
		} catch (DuplicateEndroitException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@GetMapping("/Endroits/{id}")
	public ResponseEntity<Endroit> getEndroitById(@PathVariable Long id) throws NotFound {
		Endroit endroit = endroitService.getEndroitById(id);
		return ResponseEntity.ok(endroit);
	}
	@GetMapping("/Endroits/authenticatedUser")
	public ResponseEntity<List<Endroit>> getEndroitsByAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		try {
			List<Endroit> endroits = endroitService.getEndroitsByAuthenticatedUser(username);
			return ResponseEntity.ok(endroits);
		} catch (NotFound e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@PutMapping("/Endroits/{id}")
	public ResponseEntity<Endroit> updateEndroit(@PathVariable Long id, @RequestBody Endroit endroitDetails) {
		try {
			Endroit updatedEndroit = endroitService.updateEndroit(id, endroitDetails);
			return ResponseEntity.ok(updatedEndroit);
		} catch (DuplicateEndroitException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		} catch (NotFound e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@DeleteMapping("/Endroits/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEndroit(@PathVariable Long id) throws NotFound {
		endroitService.deleteEndroit(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
