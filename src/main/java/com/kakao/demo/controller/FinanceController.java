package com.kakao.demo.controller;

import com.kakao.demo.domain.FinanceStatus;
import com.kakao.demo.service.FinanceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FinanceController {
    private final FinanceService financeService;

    public FinanceController(final FinanceService financeService) {
        this.financeService = financeService;
    }

    @GetMapping("/load/csv")
    public FinanceStatus findFinanceStatus() {
        return financeService.loadFinanceStatus();
    }

}
