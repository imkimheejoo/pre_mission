package com.kakao.demo.controller;

import com.kakao.demo.service.FinanceService;
import com.kakao.demo.service.dto.TotalPriceOfInstitutionByYear;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity findFinanceStatus() {
        financeService.loadCsvFile();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/find/price")
    public ResponseEntity<List<TotalPriceOfInstitutionByYear>> findFinanceInstitutions() {
        List<TotalPriceOfInstitutionByYear> totalPriceOfInstitutionscByYearByYear = financeService.findTotalPriceOfInstitutionsByYear();
        return new ResponseEntity<>(totalPriceOfInstitutionscByYearByYear, HttpStatus.OK);
    }

}
