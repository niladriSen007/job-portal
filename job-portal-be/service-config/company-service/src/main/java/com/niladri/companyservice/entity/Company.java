package com.niladri.companyservice.entity;

import com.niladri.domain.CompanySize;
import com.niladri.domain.CompanyStatus;
import com.niladri.domain.CompanyType;
import com.niladri.domain.IndustryType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    @Email
    private String email;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Column(unique = true, nullable = false)
    private String slug;

    @Column(unique = true, nullable = false)
    private String description;

    private String tagline;

    private String companyLogo;

    private String companyCoverImage;

    private String website;

    private Integer foundedYear;

    @Enumerated(EnumType.STRING)
    private CompanySize companySize;

    @Enumerated(EnumType.STRING)
    private CompanyType companyType;

    @Enumerated(EnumType.STRING)
    private IndustryType industryType;

    @Enumerated(EnumType.STRING)
    private CompanyStatus companyStatus;

    @Column(unique = true, nullable = false)
    private String registrationNumber;

    @Column(unique = true, nullable = false)
    private Long ownerId;

    @Column(unique = true, nullable = false)
    @Email
    private String ownerEmail;

    @ElementCollection
    List<SocialLinks> socialLinks = new ArrayList<>();

    private Boolean isActive = false;
    private Boolean isVerified = false;
    private Boolean isDeleted = false;
    private Boolean isSuspended = false;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    private LocalDateTime suspendedAt;

    private LocalDateTime verifiedAt;

}
