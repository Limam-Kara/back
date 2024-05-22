package com.example.demo.Service;

import com.example.demo.exception.NotFound;
import com.example.demo.model.Endroit;

import java.util.List;

public interface EndroitService {
    List<Endroit> getAllEndroits();
    Endroit createEndroit(Endroit endroit);
    Endroit getEndroitById(Long id) throws NotFound;
    Endroit updateEndroit(Long id, Endroit endroitDetails) throws NotFound;
    void deleteEndroit(Long id) throws NotFound;
    boolean existsByDistrictAndAnnexe(Long districtId, Long annexeId);
    public List<Endroit> getEndroitsByAuthenticatedUser(String username);
}
