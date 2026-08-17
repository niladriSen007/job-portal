package com.niladri.jobservice.service.impl;

import com.niladri.jobservice.dto.request.JobFilterRequest;
import com.niladri.jobservice.dto.request.JobRequest;
import com.niladri.jobservice.dto.request.UpdateJobRequest;
import com.niladri.jobservice.dto.response.JobResponse;
import com.niladri.jobservice.entity.Location;
import com.niladri.jobservice.mapper.Mapper;
import com.niladri.jobservice.repository.JobRepository;
import com.niladri.jobservice.service.IJobService;
import com.niladri.jobservice.specifications.JobSpecification;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JobServiceImpl implements IJobService {

    JobRepository jobRepository;

    @Override
    public JobResponse createJob(JobRequest jobRequest) {
        return null;
    }

    @Override
    public JobResponse updateJob(UpdateJobRequest jobRequest) {
        return null;
    }

    @Override
    public Page<JobResponse> getAllJobs(JobFilterRequest jobFilterRequest, Pageable pageable) {
        return jobRepository.findAll(JobSpecification.filter(jobFilterRequest), pageable)
                .map(Mapper::toJobResponse);
    }

    @Override
    public JobResponse getJobById(Long id) {
        return null;
    }

    @Override
    public Page<JobResponse> getJobsByCompany(Long companyId, Pageable pageable) {
        return null;
    }

    @Override
    public JobResponse publishJob(Long jobId) {
        return null;
    }

    @Override
    public JobResponse closeJob(Long jobId) {
        return null;
    }

    @Override
    public JobResponse deleteJob(Long jobId) {
        return null;
    }
}
