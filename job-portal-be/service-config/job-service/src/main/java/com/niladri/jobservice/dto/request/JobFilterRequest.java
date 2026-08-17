package com.niladri.jobservice.dto.request;

import com.niladri.domain.ExperienceLevel;
import com.niladri.domain.JobStatus;
import com.niladri.domain.JobType;
import com.niladri.domain.WorkMode;
import com.niladri.jobservice.entity.SalaryRange;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public record JobFilterRequest(
        SalaryRange salaryRange,
        JobType jobType,
        WorkMode workMode,
        ExperienceLevel experienceLevel,
        JobStatus jobStatus,
        String search,
        Long categoryId,
        List<Long> skills,
        Set<Long> locations,
        BigDecimal minSalary,
        BigDecimal maxSalary
) {
}
