package com.example.demo.Service;

import com.example.demo.exception.NotFound;
import com.example.demo.model.District;
import com.example.demo.repository.DistrictRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public List<District> getAllDistricts() {
//        return districtRepository.findAll();
    	 return districtRepository.findAllExceptId5();
    }

    @Override
    public District createDistrict(District district) {
        return districtRepository.save(district);
    }

    @Override
    public District getDistrictById(Long id) throws NotFound {
        return districtRepository.findById(id)
                .orElseThrow(() -> new NotFound("District not exist with id: " + id));
    }

    @Override
    public District updateDistrict(Long id, District districtDetails) throws NotFound {
        District district = getDistrictById(id);

        district.setNom(districtDetails.getNom());

        return districtRepository.save(district);
    }

    @Override
    @Transactional
    public void deleteDistrict(Long districtId) {
        District district = districtRepository.findById(districtId)
                .orElseThrow(() -> new RuntimeException("District not found"));

        // Detach related entities
        district.getUtilisateurs().forEach(utilisateur -> utilisateur.setDistrict(null));
        district.getAnnexes().forEach(annexe -> annexe.setDistrict(null));

        // Save detached entities
        districtRepository.save(district);

        // Now delete the district
        districtRepository.delete(district);
    }
}
