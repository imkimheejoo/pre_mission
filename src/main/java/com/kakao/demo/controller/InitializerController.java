package com.kakao.demo.controller;

import com.kakao.demo.service.FinanceAmountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitializerController {
    private final FinanceAmountService financeAmountService;

    public InitializerController(final FinanceAmountService financeAmountService) {
        this.financeAmountService = financeAmountService;
    }

    @GetMapping("/api/load")
    public ResponseEntity loadFinanceData() {
        financeAmountService.loadCsvFile();
        return ResponseEntity.ok().build();
    }
}
