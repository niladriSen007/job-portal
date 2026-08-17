package com.niladri.jobservice.mapper;

import com.niladri.jobservice.dto.response.JobResponse;
import com.niladri.jobservice.entity.Job;

public class Mapper {
    public static JobResponse toJobResponse(Job job) {
        return new JobResponse(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getRequirements(),
                job.getResponsibilities(),
                job.getLocations(),
                job.getSalaryRange().getMinSalary(),
                job.getSalaryRange().getMaxSalary(),
                job.getJobType(),
                job.getSalaryRange(),
                job.getWorkMode(),
                job.getJobStatus(),
                job.getExperienceLevel(),
                job.getOpenings(),
                job.getApplicationDeadline(),
                job.getExpiresAt(),
                job.getIsActive(),
                job.getCreatedAt(),
                job.getUpdatedAt(),
                job.getPublishedAt(),
                job.getClosedAt()
        );
    }
}
