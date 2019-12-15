package com.kakao.demo.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class FinanceAmount {
    public static final int MIN_AMOUNT = 0;
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

    private FinanceAmount(int amount, FinanceDate financeDate, Institution institution) {
        this.amount = amount;
        this.financeDate = financeDate;
        this.institution = institution;
    }

    public static FinanceAmount of(int amount, FinanceDate date, Institution institution) {
        checkValidAmount(amount);
        return new FinanceAmount(amount, date, institution);
    }

    private static void checkValidAmount(int amount) {
        if (amount < MIN_AMOUNT) {
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