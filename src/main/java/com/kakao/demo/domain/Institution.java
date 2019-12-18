package com.kakao.demo.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
public class Institution {
    private static final String INSTITUTION_CODE_PREFIX = "bnk_";
    private static AtomicInteger atomicInteger = new AtomicInteger();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String code;

    protected Institution() {
    }

    private Institution(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public static Institution from(String name) {
        return new Institution(name, INSTITUTION_CODE_PREFIX + atomicInteger.getAndIncrement());
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Institution that = (Institution) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
