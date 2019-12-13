package com.kakao.demo.service;

import com.kakao.demo.domain.AmountRepository;
import com.kakao.demo.domain.FinanceAmount;
import com.kakao.demo.domain.FinanceDate;
import com.kakao.demo.domain.Institution;
import com.kakao.demo.utils.DataConverter;
import com.kakao.demo.utils.DataLoader;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FinanceService {
    private static final int COLUMN_NAME_INDEX = 0;
    public static final int YEAR_INDEX = 0;
    public static final int MONTH_INDEX = 1;

    private final InstitutionService institutionService;
    private final AmountRepository amountRepository;

    public FinanceService(InstitutionService institutionService, AmountRepository amountRepository) {
        this.institutionService = institutionService;
        this.amountRepository = amountRepository;
    }

    private void create(List<String> financeStatusByDate, List<InstitutionDto> institutionDtos) {
        FinanceDate financeDate = new FinanceDate(financeStatusByDate.get(YEAR_INDEX), financeStatusByDate.get(MONTH_INDEX));

        // TODO: 13/12/2019 데이터 가공로직 많다,,,
        List<String> financeStatus = DataConverter.extractInstitutionValues(financeStatusByDate);
        List<Integer> financeOfInstitution =
                DataConverter.convertInputToRealResult(financeStatus);

        for (int i = 0; i < financeOfInstitution.size(); i++) {
            Institution institution = institutionService.findByName(institutionDtos.get(i).getName());
            int money = financeOfInstitution.get(i);
            FinanceAmount financeAmount = new FinanceAmount(money, financeDate, institution);
            amountRepository.save(financeAmount);
        }
    }

    public void saveInputFile() {
        List<String[]> inputData = DataLoader.loadCsvFile();
        //은행 정보 저장
        List<InstitutionDto> institutionDtos = institutionService.saveInstitutions(
                Arrays.asList(inputData.get(COLUMN_NAME_INDEX)));

        //Amount 저장
        for (int i = 1; i < inputData.size(); i++) {
            List<String> data = DataConverter.deleteEmptyValue(Arrays.asList(inputData.get(i)));
            create(data, institutionDtos);
        }
    }
}

