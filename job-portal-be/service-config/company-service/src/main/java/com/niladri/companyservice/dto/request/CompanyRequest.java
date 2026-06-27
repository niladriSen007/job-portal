package com.niladri.companyservice.dto.request;

import com.niladri.companyservice.dto.response.SocialLinkResponse;
import com.niladri.companyservice.dto.response.SocialLinkResponse;
import com.niladri.domain.CompanySize;
import com.niladri.domain.CompanyType;
import com.niladri.domain.IndustryType;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyRequest {

    @NotBlank(message = "Company name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    private String slug;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Invalid phone number format")
    private String phoneNumber;

    @NotBlank(message = "Description is required")
    private String description;

    private String tagline;

    private String companyLogo;

    private String companyCoverImage;

    @Pattern(regexp = "^(https?://).*", message = "Company Website URL must be valid")
    private String website;

    @Min(value = 1800, message = "Founded year seems too old")
    @Max(value = 2100, message = "Founded year is invalid")
    private Integer foundedYear;

    @NotNull(message = "Company size is required")
    private CompanySize companySize;

    @NotNull(message = "Company type is required")
    private CompanyType companyType;

    @NotNull(message = "Industry type is required")
    private IndustryType industryType;

    @NotBlank(message = "Registration number is required")
    private String registrationNumber;

//    @NotNull(message = "Owner ID is required")
    private Long ownerId;

    @NotBlank(message = "Owner email is required")
    @Email(message = "Invalid owner email format")
    private String ownerEmail;

    private List<SocialLinkResponse> socialLinks = new ArrayList<>();
}
