package com.kakao.demo.domain;

import com.kakao.demo.service.dto.DetailAmountsOfInstitutionByYear;
import com.kakao.demo.service.dto.SupportedAmountOfInstitution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AmountRepository extends JpaRepository<FinanceAmount, Long> {

    @Query("select new com.kakao.demo.service.dto.DetailAmountsOfInstitutionByYear(" +
            "f.financeDate.year, i.name, sum(f.amount)) " +
            "from Institution i left join FinanceAmount f " +
            "on i.id = f.institution.id " +
            "group by i.name ,f.financeDate.year " +
            "order by f.financeDate.year")
    List<DetailAmountsOfInstitutionByYear> findTotalAmountGroupByInstitutionAndYear();

    @Query(value = "select g.year, g.name " +
            "from (select f.year as year , i.name as name , sum(f.amount) as total " +
            "from INSTITUTION i left join FINANCE_AMOUNT f " +
            "on i.id = f.institution_id " +
            "group by i.name ,f.year) g " +
            "order by g.total desc limit 1", nativeQuery = true)
    List<Object[]> findInstitutionAndYearWithTheHighestAmount();

    @Query(value = "select new com.kakao.demo.service.dto.SupportedAmountOfInstitution(f.financeDate.year, round(avg(f.amount))) " +
            "from FinanceAmount f , Institution i " +
            "where f.institution.name = :institution " +
            "group by f.financeDate.year")
    List<SupportedAmountOfInstitution> findAverageAmountByInstitutionName(@Param("institution") String institution);
}
