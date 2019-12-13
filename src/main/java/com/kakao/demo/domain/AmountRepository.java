package com.kakao.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AmountRepository extends JpaRepository<FinanceAmount, Long> {
}
