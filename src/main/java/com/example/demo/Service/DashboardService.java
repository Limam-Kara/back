package com.example.demo.Service;

public interface DashboardService {
    long countUtilisateurs();
    long countAnnexes();
    long countDistricts();
    long countCategories();
    long countEndroitsByUtilisateur(String username);
}
