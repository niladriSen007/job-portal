package com.niladri.jobservice.entity;

import com.niladri.domain.ExperienceLevel;
import com.niladri.domain.JobStatus;
import com.niladri.domain.JobType;
import com.niladri.domain.WorkMode;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    private String requirements;

    private String responsibilities;

    @ManyToMany
    @JoinTable(
            name = "job_locations",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id")
    )
    private Set<Location> locations = new HashSet<>();

    private Long companyId;

//    private JobCategory category;
//
//    private Set<JobSkill> skills = new HashSet<>();
//
//    private Set<JobTag> tags = new HashSet<>();

    @Embedded
    private SalaryRange salaryRange;

    @Enumerated(EnumType.STRING)
    private JobType jobType;

    @Enumerated(EnumType.STRING)
    private WorkMode workMode;

    @Enumerated(EnumType.STRING)
    private ExperienceLevel experienceLevel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private JobStatus jobStatus = JobStatus.DRAFT;

    private Integer openings=1;

    private LocalDate applicationDeadline;

    private LocalDate expiresAt;

    private Boolean isActive = true;

    @Column(nullable = false,updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDateTime publishedAt;
    private LocalDateTime closedAt;

}
