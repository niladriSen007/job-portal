package com.niladri.companyservice.dto.request;

import com.niladri.domain.CompanySize;
import com.niladri.domain.CompanyStatus;
import com.niladri.domain.CompanyType;
import com.niladri.domain.IndustryType;

public record CompanyFilterRequest(
        CompanySize companySize,
        CompanyType companyType,
        IndustryType industryType,
        CompanyStatus companyStatus,
        String search) {
}
