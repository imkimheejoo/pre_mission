package com.kakao.demo.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class FinanceAmount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int money;

    @Embedded
    private FinanceDate financeDate;

    @ManyToOne
    private Institution institution;

    protected FinanceAmount() {
    }

    public FinanceAmount(int money, FinanceDate financeDate, Institution institution) {
        this.money = money;
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