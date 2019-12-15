package com.kakao.demo.domain;

import com.kakao.demo.service.dto.DetailAmountsOfInstitutionByYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AmountRepository extends JpaRepository<FinanceAmount, Long> {

    @Query("select new com.kakao.demo.service.dto.DetailAmountsOfInstitutionByYear(" +
            "f.financeDate.year, i.name, sum(f.price)) " +
            "from Institution i left join FinanceAmount f " +
            "on i.id = f.institution.id " +
            "group by i.name ,f.financeDate.year")
    List<DetailAmountsOfInstitutionByYear> findTotalPriceGroupByInstitutionAndYear();

}
