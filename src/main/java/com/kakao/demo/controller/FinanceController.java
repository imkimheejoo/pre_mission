package com.kakao.demo.controller;

import com.kakao.demo.service.FinanceService;
import com.kakao.demo.service.dto.AmountByYear;
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
    public ResponseEntity findFinanceData() {
        financeService.loadCsvFile();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/find/status")
    public ResponseEntity findTotalAmountAboutInstitutionByYear(Model model) {
        List<AmountByYear> totalAmountOfInstitutionsByYear = financeService.findTotalAmountOfInstitutionsByYear();
        model.addAttribute("name", "주택금융 공급현황");
        model.addAttribute("amountsByYear", totalAmountOfInstitutionsByYear);

        return ResponseEntity.ok(model);
    }
}
