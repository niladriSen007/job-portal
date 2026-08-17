package com.niladri.jobservice.service;

import com.niladri.jobservice.dto.request.JobFilterRequest;
import com.niladri.jobservice.dto.request.JobRequest;
import com.niladri.jobservice.dto.request.UpdateJobRequest;
import com.niladri.jobservice.dto.response.JobResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IJobService {

    JobResponse createJob(JobRequest jobRequest);

    JobResponse updateJob(UpdateJobRequest jobRequest);

    Page<JobResponse> getAllJobs(JobFilterRequest jobFilterRequest, Pageable pageable);

    JobResponse getJobById(Long id);

    Page<JobResponse> getJobsByCompany(Long companyId, Pageable pageable);

    JobResponse publishJob(Long jobId);

    JobResponse closeJob(Long jobId);

    JobResponse deleteJob(Long jobId);
}
