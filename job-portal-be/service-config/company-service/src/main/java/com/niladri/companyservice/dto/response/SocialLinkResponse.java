package com.niladri.companyservice.dto.response;

import com.niladri.domain.SocialPlatform;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SocialLinkResponse {
    private SocialPlatform socialPlatform;
    private String url;
}
