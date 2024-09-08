package com.irojas.demojwt.studentDay.presentation.DTO;

import com.irojas.demojwt.Utilities.Enum.StateEnum;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class StudyDayDto {

    private String studyDay;
    private String description;
    private StateEnum State;
    
}
