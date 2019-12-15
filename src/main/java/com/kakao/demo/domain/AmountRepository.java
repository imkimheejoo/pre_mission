package com.kakao.demo.domain;

import com.kakao.demo.service.dto.TotalPriceOfInstitutionByYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AmountRepository extends JpaRepository<FinanceAmount, Long> {
    @Query("select new com.kakao.demo.service.dto.TotalPriceOfInstitutionByYear(f.financeDate.year, f.institution.id, sum(f.price)) from FinanceAmount f group by f.institution.id ,f.financeDate.year")
    List<TotalPriceOfInstitutionByYear> findTotalOfDetailFinancePriceGroupByInstitutionAndMonth();

}
