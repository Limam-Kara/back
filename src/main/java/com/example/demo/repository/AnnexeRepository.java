package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Annexe;

@Repository
public interface AnnexeRepository extends JpaRepository<Annexe, Long> {

}
