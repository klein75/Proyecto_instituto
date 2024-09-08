package com.irojas.demojwt.studentDay.Services.interfaces;

import java.util.List;
import java.util.Optional;

import com.irojas.demojwt.Utilities.Enum.StateEnum;

import com.irojas.demojwt.studentDay.presentation.DTO.StudyDayDto;

public interface IStudyDayServices {

   StudyDayDto saveStudyDay(StudyDayDto studyDayDto);
   Optional<StudyDayDto> findById(Long id);
   Optional<StudyDayDto> findByStudyDay(String jornada);
   Optional<StudyDayDto> findByState(StateEnum state);
   List<StudyDayDto> findAllStudyDay();

   StudyDayDto updateStudyDay(Long id, StudyDayDto studyDayDto);

    void deleteStudyDay(Long id);




}
