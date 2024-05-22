package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.District;
@Repository

public interface DistrictRepository extends JpaRepository<District, Long>{
	  @Query("SELECT d FROM District d WHERE d.id <> 5")
    List<District> findAllExceptId5();
}
