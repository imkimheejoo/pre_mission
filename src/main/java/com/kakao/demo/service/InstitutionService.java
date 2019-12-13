package com.kakao.demo.service;

import com.kakao.demo.domain.Institution;
import com.kakao.demo.domain.InstitutionRepository;
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
        // TODO: 13/12/2019 데이터 가공 로직이 너무 많음,,,,,,,,
        List<String> validData = DataConverter.deleteEmptyValue(institutionNames);
        List<String> institutions = DataConverter.deleteBenchMark(DataConverter.extractInstitutionNames(validData));

        for (String institution : institutions) {
            save(institution);
            institutionDtos.add(new InstitutionDto(institution));
        }
        return institutionDtos;
    }
}
