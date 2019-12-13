package com.kakao.demo.controller;

import com.kakao.demo.service.FinanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FinanceController {
    private final FinanceService financeService;

    public FinanceController(final FinanceService financeService) {
        this.financeService = financeService;
    }

    @GetMapping("/api/load")
    public ResponseEntity findFinanceStatus() {
        financeService.saveInputFile();
        return ResponseEntity.ok().build();
    }

}
