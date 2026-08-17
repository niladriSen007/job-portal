package com.niladri.jobservice.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Embeddable
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalaryRange {
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
}
