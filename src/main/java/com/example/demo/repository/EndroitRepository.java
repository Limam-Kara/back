package com.example.demo.repository;

import com.example.demo.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Endroit;

import java.util.List;
import java.util.Optional;

@Repository
public interface EndroitRepository extends JpaRepository<Endroit, Long>{
    Optional<Endroit> findByDistrictIdAndAnnexeId(Long districtId, Long annexeId);
    long countByUtilisateurUsername(String username);
    List<Endroit> findByUtilisateur(Utilisateur utilisateur);
}
