package com.kakao.demo.controller;

import com.kakao.demo.service.FinanceService;
import com.kakao.demo.service.dto.AmountsByYear;
import com.kakao.demo.service.dto.InstitutionOfTheHighestAmount;
import com.kakao.demo.service.dto.StatisticAboutInstitution;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FinanceController {
    private final FinanceService financeService;

    public FinanceController(final FinanceService financeService) {
        this.financeService = financeService;
    }

    @GetMapping("/api/load")
    public ResponseEntity loadFinanceData() {
        financeService.loadCsvFile();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/find/status")
    public ResponseEntity findTotalAmountAboutInstitutionByYear(Model model) {
        List<AmountsByYear> totalAmountOfInstitutionsByYear = financeService.findTotalAmountOfInstitutionsByYear();
        model.addAttribute("name", "주택금융 공급현황");
        model.addAttribute("amountsByYear", totalAmountOfInstitutionsByYear);

        return ResponseEntity.ok(model);
    }

    @GetMapping("/api/find/max/institution")
    public ResponseEntity findInstitutionOfMaxAmount(Model model) {
        InstitutionOfTheHighestAmount institutionAndYearWithTheHighestAmount = financeService.findInstitutionAndYearWithTheHighestAmount();
        model.addAttribute("year",institutionAndYearWithTheHighestAmount.getYear());
        model.addAttribute("bank",institutionAndYearWithTheHighestAmount.getInstitution());

        return ResponseEntity.ok(model);
    }

    @GetMapping("/api/find/statistic/keb")
    public ResponseEntity findStatisticAboutKEB(Model model) {
        StatisticAboutInstitution statisticOfInstitution = financeService.findStatisticAboutInstitution("외환은행");

        model.addAttribute("bank", statisticOfInstitution.getInstitution());
        model.addAttribute("support_amount", statisticOfInstitution.getSupportedAmount());

        return ResponseEntity.ok(model);
    }
}
