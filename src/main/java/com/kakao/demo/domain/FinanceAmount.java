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

    public FinanceAmount(int amount, FinanceDate financeDate, Institution institution) {
        checkValidAmount(amount);
        this.amount = amount;
        this.financeDate = financeDate;
        this.institution = institution;
    }

    private void checkValidAmount(int amount) {
        if(amount < 0) {
            throw new InvalidAmountTypeException();
        }
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