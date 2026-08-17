package com.niladri.jobservice.dto.response;

import com.niladri.domain.ExperienceLevel;
import com.niladri.domain.JobStatus;
import com.niladri.domain.JobType;
import com.niladri.domain.WorkMode;
import com.niladri.jobservice.entity.Location;
import com.niladri.jobservice.entity.SalaryRange;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;


public record JobResponse(
        Long id,
        String title,
        String description,
        String requirements,
        String responsibilities,
        Set<Location> locations,
        BigDecimal minSalary,
        BigDecimal maxSalary,
        JobType jobType,
        SalaryRange salaryRange,
        WorkMode workMode,
        JobStatus jobStatus,
        ExperienceLevel experienceLevel,
        Integer openings,
        LocalDate applicationDeadline,
        LocalDate expiresAt,
        Boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime publishedAt,
        LocalDateTime closedAt
) {
}
