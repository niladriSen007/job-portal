package com.niladri.jobservice.dto.request;

import com.niladri.domain.ExperienceLevel;
import com.niladri.domain.JobType;
import com.niladri.domain.WorkMode;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobRequest {
    @NotBlank(message = "Job title is mandatory")
    private String jobTitle;

    @NotBlank(message = "Job description is mandatory")
    private String jobDescription;

    @NotBlank(message = "Job requirements are mandatory")
    private String jobRequirements;

    private String responsibilities;

    @NotNull(message = "Category ID is mandatory")
    private Long categoryId;

    private Set<Long> skillIds;
    private Set<Long> tagIds;

    private String address;
    private String city;
    private String country;
    private String state;
    private String zipcode;

    private Boolean isActive;

    @DecimalMin(value = "0.0", inclusive = false, message = "Minimum salary must be greater than 0")
    private BigDecimal minSalary;
    @DecimalMin(value = "0.0", inclusive = false, message = "Maximum salary must be greater than 0")
    private BigDecimal maxSalary;

    @NotNull(message = "Job type is mandatory")
    private JobType jobType;

    @NotNull(message = "Work mode is mandatory")
    private WorkMode workMode;

    @NotNull(message = "Experience level is mandatory")
    private ExperienceLevel experienceLevel;

    @Min(value = 1, message = "Openings must be at least 1")
    private Integer openings;

    private LocalDate applicationDeadline;
    private LocalDate expiresAt;

}
