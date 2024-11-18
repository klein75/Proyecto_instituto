package com.instituto.demoj.Profile.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileDto {

    private Long userId; 
    private String bio; 
    private String image;

}
