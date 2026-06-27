package com.niladri.companyservice.specification;

import org.springframework.data.jpa.domain.Specification;

import com.niladri.companyservice.dto.request.CompanyFilterRequest;
import com.niladri.companyservice.entity.Company;

public class CompanySpecification {
    public static Specification<Company> filter(CompanyFilterRequest filterRequest) {
        return (root, query, criteriaBuilder) -> {
            var predicates = criteriaBuilder.conjunction();

            if (filterRequest.companySize() != null) {
                predicates.getExpressions().add(
                        criteriaBuilder.equal(root.get("companySize"), filterRequest.companySize()));
            }

            if (filterRequest.companyType() != null) {
                predicates.getExpressions().add(
                        criteriaBuilder.equal(root.get("companyType"), filterRequest.companyType()));
            }

            if (filterRequest.industryType() != null) {
                predicates.getExpressions().add(
                        criteriaBuilder.equal(root.get("industryType"), filterRequest.industryType()));
            }

            if (filterRequest.companyStatus() != null) {
                predicates.getExpressions().add(
                        criteriaBuilder.equal(root.get("companyStatus"), filterRequest.companyStatus()));
            }

            if (filterRequest.search() != null && !filterRequest.search().isEmpty()) {
                String searchPattern = "%" + filterRequest.search().toLowerCase() + "%";
                predicates.getExpressions().add(
                        criteriaBuilder.or(
                                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), searchPattern)));
            }

            return predicates;
        };
    }
}
