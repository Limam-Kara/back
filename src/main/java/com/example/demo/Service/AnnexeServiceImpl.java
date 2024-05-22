package com.example.demo.Service;

import com.example.demo.exception.NotFound;
import com.example.demo.model.Annexe;
import com.example.demo.repository.AnnexeRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnexeServiceImpl implements AnnexeService {

    @Autowired
    private AnnexeRepository annexeRepository;

    @Override
    public List<Annexe> getAllAnnexes() {
        return annexeRepository.findAll();
    }

    @Override
    public Annexe createAnnexe(Annexe annexe) {
        return annexeRepository.save(annexe);
    }

    @Override
    public Annexe getAnnexeById(Long id) throws NotFound {
        return annexeRepository.findById(id)
                .orElseThrow(() -> new NotFound("Annexe not exist with id: " + id));
    }

    @Override
    public Annexe updateAnnexe(Long id, Annexe annexeDetails) throws NotFound {
        Annexe annexe = getAnnexeById(id);

        annexe.setNom(annexeDetails.getNom());
        annexe.setAdresse(annexeDetails.getAdresse());
        annexe.setDistrict(annexeDetails.getDistrict());

        return annexeRepository.save(annexe);
    }

    @Override
    public void deleteAnnexe(Long id) throws NotFound {
        Annexe annexe = getAnnexeById(id);
        annexeRepository.delete(annexe);
    }
}
