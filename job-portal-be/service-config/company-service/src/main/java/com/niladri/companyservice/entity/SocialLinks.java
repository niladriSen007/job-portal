package com.niladri.companyservice.entity;

import com.niladri.domain.SocialPlatform;
import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocialLinks {
    private SocialPlatform socialPlatform;
    private String url;

}
