package com.example.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.Service.DistrictService;
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
import com.example.demo.model.District;
import com.example.demo.repository.DistrictRepository;

@RestController
@RequestMapping("/SGI/District/")


public class DistrictController {

	@Autowired
	private DistrictService districtService;

	@GetMapping("/Districts")
	public List<District> getAllDistricts() {
		return districtService.getAllDistricts();
	}

	@PostMapping("/Districts")
	public District createDistrict(@RequestBody District district) {
		return districtService.createDistrict(district);
	}

	@GetMapping("/Districts/{id}")
	public ResponseEntity<District> getDistrictById(@PathVariable Long id) throws NotFound {
		District district = districtService.getDistrictById(id);
		return ResponseEntity.ok(district);
	}

	@PutMapping("/Districts/{id}")
	public ResponseEntity<District> updateDistrict(@PathVariable Long id, @RequestBody District districtDetails) throws NotFound {
		District updatedDistrict = districtService.updateDistrict(id, districtDetails);
		return ResponseEntity.ok(updatedDistrict);
	}

	@DeleteMapping("/Districts/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteDistrict(@PathVariable Long id) throws NotFound {
		districtService.deleteDistrict(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}