package com.example.demo.Service;

import com.example.demo.model.Annexe;
import com.example.demo.exception.NotFound;

import java.util.List;

public interface AnnexeService {
    List<Annexe> getAllAnnexes();
    Annexe createAnnexe(Annexe annexe);
    Annexe getAnnexeById(Long id) throws NotFound;
    Annexe updateAnnexe(Long id, Annexe annexeDetails) throws NotFound;
    void deleteAnnexe(Long id) throws NotFound;
}