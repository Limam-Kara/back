package com.example.demo.Service;

import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private EndroitRepository endroitRepository;

    @Autowired
    private AnnexeRepository annexeRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    @Override
    public long countUtilisateurs() {
        return utilisateurRepository.count();
    }

    @Override
    public long countAnnexes() {
        return annexeRepository.count();
    }

    @Override
    public long countDistricts() {
        return districtRepository.count();
    }

    @Override
    public long countCategories() {
        return categorieRepository.count();
    }

    @Override
    public long countEndroitsByUtilisateur(String username) {
        return endroitRepository.countByUtilisateurUsername(username);
    }
}
