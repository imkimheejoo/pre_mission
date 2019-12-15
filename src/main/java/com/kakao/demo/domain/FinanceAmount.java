package com.kakao.demo.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class FinanceAmount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int amount;

    @Embedded
    private FinanceDate financeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Institution institution;

    protected FinanceAmount() {
    }

    // TODO: 15/12/2019 amount 는 0 미만이 될 수 없음
    public FinanceAmount(int amount, FinanceDate financeDate, Institution institution) {
        this.amount = amount;
        this.financeDate = financeDate;
        this.institution = institution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinanceAmount that = (FinanceAmount) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}