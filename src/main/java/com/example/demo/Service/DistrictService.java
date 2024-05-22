package com.example.demo.Service;

import com.example.demo.exception.NotFound;
import com.example.demo.model.District;

import java.util.List;

public interface DistrictService {
    List<District> getAllDistricts();
    District createDistrict(District district);
    District getDistrictById(Long id) throws NotFound;
    District updateDistrict(Long id, District districtDetails) throws NotFound;
    void deleteDistrict(Long id) throws NotFound;
}
