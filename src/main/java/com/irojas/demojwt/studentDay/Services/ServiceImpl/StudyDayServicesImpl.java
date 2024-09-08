package com.irojas.demojwt.studentDay.Services.ServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irojas.demojwt.Utilities.Enum.StateEnum;
import com.irojas.demojwt.studentDay.Persistence.Entity.StudyDay;
import com.irojas.demojwt.studentDay.Persistence.Repository.StudyDayRespository;
import com.irojas.demojwt.studentDay.Services.interfaces.IStudyDayServices;
import com.irojas.demojwt.studentDay.presentation.DTO.StudyDayDto;

@Service
public class StudyDayServicesImpl implements IStudyDayServices {
    @Autowired
    private StudyDayRespository studyDayRepository;

  
    private StudyDayDto toDto(StudyDay studyDay) {
        return StudyDayDto.builder()
                .studyDay(studyDay.getStudyDay())
                .description(studyDay.getDescription())
                .State(studyDay.getState()) 
                .build();
    }

    private StudyDay toEntity(StudyDayDto dto) {
        return StudyDay.builder()
                .studyDay(dto.getStudyDay())
                .description(dto.getDescription())
                .state(dto.getState()) 
                .build();
    }

    @Override
    public StudyDayDto saveStudyDay(StudyDayDto studyDayDto) {
        StudyDay studyDay = toEntity(studyDayDto);
        StudyDay savedStudyDay = studyDayRepository.save(studyDay);
        return toDto(savedStudyDay);
    }

    @Override
    public Optional<StudyDayDto> findById(Long id) {
        return studyDayRepository.findById(id)
                .map(this::toDto);
    }

    @Override
    public Optional<StudyDayDto> findByStudyDay(String jornada) {
        return studyDayRepository.findByStudyDay(jornada)
                .map(this::toDto);
    }

    @Override
    public Optional<StudyDayDto> findByState(StateEnum state) {
        return studyDayRepository.findByState(state)
                .map(this::toDto);
    }

    @Override
    public List<StudyDayDto> findAllStudyDay() {
        return studyDayRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudyDayDto updateStudyDay(Long id, StudyDayDto studyDayDto) {
        StudyDay existingStudyDay = studyDayRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("StudyDay not found"));

        existingStudyDay.setStudyDay(studyDayDto.getStudyDay());
        existingStudyDay.setDescription(studyDayDto.getDescription());
        existingStudyDay.setState(studyDayDto.getState());

        StudyDay updatedStudyDay = studyDayRepository.save(existingStudyDay);
        return toDto(updatedStudyDay);
    }

    @Override
    public void deleteStudyDay(Long id) {
        studyDayRepository.deleteById(id);
    }
}
