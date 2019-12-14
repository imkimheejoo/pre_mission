package com.kakao.demo.service;

import com.kakao.demo.domain.AmountRepository;
import com.kakao.demo.domain.FinanceAmount;
import com.kakao.demo.domain.FinanceDate;
import com.kakao.demo.domain.Institution;
import com.kakao.demo.service.dto.InstitutionDto;
import com.kakao.demo.service.dto.Measures;
import com.kakao.demo.utils.DataConverter;
import com.kakao.demo.utils.DataLoader;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinanceService {
    private static final int COLUMN_INDEX = 0;

    private final InstitutionService institutionService;
    private final AmountRepository amountRepository;

    public FinanceService(InstitutionService institutionService, AmountRepository amountRepository) {
        this.institutionService = institutionService;
        this.amountRepository = amountRepository;
    }

    private void save(List<Measures> measuresByDate, List<InstitutionDto> institutionDtos) {
        for (Measures measures : measuresByDate) {
            saveByDate(measures, institutionDtos);
        }
    }

    private void saveByDate(Measures financeStatusByDate, List<InstitutionDto> institutionDtos) {
        FinanceDate financeDate = FinanceDate.of(financeStatusByDate.getYear(), financeStatusByDate.getMonth());

        for (int i = 0; i < institutionDtos.size(); i++) {
            String institutionName = institutionDtos.get(i).getName();
            Institution institution = institutionService.findByName(institutionName);

            int money = financeStatusByDate.getMeasure(i);
            FinanceAmount financeAmount = new FinanceAmount(money, financeDate, institution);
            amountRepository.save(financeAmount);
        }
    }

    public void loadCsvFile() {
        List<String[]> inputData = DataLoader.loadCsvFile();

        //기관 저장
        List<String> firstRows = Arrays.stream(inputData.get(COLUMN_INDEX)).collect(Collectors.toList());
        List<String> institutionNames = DataConverter.extractInstitutionNames(firstRows);
        List<InstitutionDto> institutionDtos = institutionService.saveInstitutions(institutionNames);

        //측정값 저장

        List<Measures> measures = DataConverter.extractMeasures(inputData.subList(1, inputData.size()));
        save(measures, institutionDtos);
    }
}

