package com.irojas.demojwt.studentDay.Persistence.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irojas.demojwt.Utilities.Enum.StateEnum;
import com.irojas.demojwt.studentDay.Persistence.Entity.StudyDay;

public interface StudyDayRespository extends JpaRepository <StudyDay, Long> {

    Optional<StudyDay> findByStudyDay(String jornada);

    Optional<StudyDay> findByState(StateEnum state);

}
