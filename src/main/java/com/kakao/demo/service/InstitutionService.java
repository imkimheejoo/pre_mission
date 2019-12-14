package com.kakao.demo.service;

import com.kakao.demo.domain.Institution;
import com.kakao.demo.domain.InstitutionRepository;
import com.kakao.demo.service.dto.InstitutionDto;
import com.kakao.demo.utils.DataConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstitutionService {
    private final InstitutionRepository institutionRepository;

    public InstitutionService(final InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    public void save(String institutionName) {
        Institution institution = new Institution(institutionName);
        institutionRepository.save(institution);
    }

    @Transactional(readOnly = true)
    public Institution findByName(String institutionName) {
        return institutionRepository.findByName(institutionName);
    }

    public List<InstitutionDto> saveInstitutions(List<String> institutionNames) {
        List<InstitutionDto> institutionDtos = new ArrayList<>();
        List<String> institutions = DataConverter.extractInstitutionNames(institutionNames);

        for (String institution : institutions) {
            save(institution);
            institutionDtos.add(new InstitutionDto(institution));
        }
        return institutionDtos;
    }
}
