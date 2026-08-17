package com.niladri.jobservice.specifications;

import com.niladri.jobservice.dto.request.JobFilterRequest;
import com.niladri.jobservice.entity.Job;
import org.springframework.data.jpa.domain.Specification;

public class JobSpecification {
    public static Specification<Job> filter(JobFilterRequest filterRequest) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            var predicates = criteriaBuilder.conjunction();

            if (filterRequest.salaryRange() != null) {
                predicates.getExpressions().add(
                        criteriaBuilder.equal(root.get("salaryRange"), filterRequest.salaryRange()));
            }

            if (filterRequest.workMode() != null) {
                predicates.getExpressions().add(
                        criteriaBuilder.equal(root.get("workMode"), filterRequest.workMode()));
            }

            if (filterRequest.experienceLevel() != null) {
                predicates.getExpressions().add(
                        criteriaBuilder.equal(root.get("experienceLevel"), filterRequest.experienceLevel()));
            }

            if (filterRequest.categoryId() != null) {
                predicates.getExpressions().add(
                        criteriaBuilder.equal(root.get("categoryId"), filterRequest.categoryId()));
            }

            if (filterRequest.locations() != null && !filterRequest.locations().isEmpty()) {
                predicates.getExpressions().add(
                        root.get("location").in(filterRequest.locations()));
            }

            if(filterRequest.skills() != null && !filterRequest.skills().isEmpty()) {
                predicates.getExpressions().add(
                        root.join("skills").get("id").in(filterRequest.skills()));
            }

            if (filterRequest.minSalary() != null) {
                predicates.getExpressions().add(
                        criteriaBuilder.greaterThanOrEqualTo(root.get("minSalary"), filterRequest.minSalary()));
            }

            if (filterRequest.maxSalary() != null) {
                predicates.getExpressions().add(
                        criteriaBuilder.lessThanOrEqualTo(root.get("maxSalary"), filterRequest.maxSalary()));
            }

            if (filterRequest.jobType() != null) {
                predicates.getExpressions().add(
                        criteriaBuilder.equal(root.get("jobType"), filterRequest.jobType()));
            }

            if (filterRequest.jobStatus() != null) {
                predicates.getExpressions().add(
                        criteriaBuilder.equal(root.get("jobStatus"), filterRequest.jobStatus()));
            }

            if (filterRequest.search() != null && !filterRequest.search().isEmpty()) {
                String searchPattern = "%" + filterRequest.search().toLowerCase() + "%";
                predicates.getExpressions().add(
                        criteriaBuilder.or(
                                criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), searchPattern),
                                criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), searchPattern)));
            }

            return predicates;


        };
    }
}
