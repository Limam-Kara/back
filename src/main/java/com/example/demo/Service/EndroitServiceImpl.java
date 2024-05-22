package com.example.demo.Service;

import com.example.demo.exception.DuplicateEndroitException;
import com.example.demo.exception.NotFound;
import com.example.demo.model.Endroit;
import com.example.demo.model.Utilisateur;
import com.example.demo.repository.EndroitRepository;


import com.example.demo.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EndroitServiceImpl implements EndroitService {

    @Autowired
    private EndroitRepository endroitRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Override
    public List<Endroit> getAllEndroits() {
        return endroitRepository.findAll();
    }

    @Override
    public Endroit createEndroit(Endroit endroit) {
        // Find the utilisateur by id, username, or any other unique field
        Utilisateur utilisateur = endroit.getUtilisateur();
        if (utilisateur != null) {
            Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(utilisateur.getId());
            if (utilisateurOptional.isPresent()) {
                utilisateur = utilisateurOptional.get();
                endroit.setUtilisateur(utilisateur);
            } else {
                throw new RuntimeException("Utilisateur not found");
            }
        }

        return endroitRepository.save(endroit);
    }

    @Override
    public Endroit getEndroitById(Long id) throws NotFound {
        return endroitRepository.findById(id)
                .orElseThrow(() -> new NotFound("Endroit not exist with id: " + id));
    }

    @Override
    public List<Endroit> getEndroitsByAuthenticatedUser(String username) {


        Utilisateur utilisateur = utilisateurRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utilisateur not found"));
        return endroitRepository.findByUtilisateur(utilisateur);
    }

    @Override
    public Endroit updateEndroit(Long id, Endroit endroitDetails) throws NotFound {
        Endroit endroit = endroitRepository.findById(id)
                .orElseThrow(() -> new NotFound("Endroit not found with id: " + id));

        endroit.setNom(endroitDetails.getNom());
        endroit.setLatitude(endroitDetails.getLatitude());
        endroit.setLongitude(endroitDetails.getLongitude());
        endroit.setDistrict(endroitDetails.getDistrict());
        endroit.setAnnexe(endroitDetails.getAnnexe());
        endroit.setCategorie(endroitDetails.getCategorie());

        Utilisateur utilisateuru = endroitDetails.getUtilisateur();
        if (utilisateuru != null) {
            Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(utilisateuru.getId());
            if (utilisateurOptional.isPresent()) {
                utilisateuru = utilisateurOptional.get();
                endroit.setUtilisateur(utilisateuru);
            } else {
                throw new RuntimeException("Utilisateur not found");
            }
        }

        return endroitRepository.save(endroit);
    }


    @Override
    public void deleteEndroit(Long id) throws NotFound {
        Endroit endroit = getEndroitById(id);
        endroitRepository.delete(endroit);
    }
    @Override
    public boolean existsByDistrictAndAnnexe(Long districtId, Long annexeId) {
        return endroitRepository.findByDistrictIdAndAnnexeId(districtId, annexeId).isPresent();
    }
}
