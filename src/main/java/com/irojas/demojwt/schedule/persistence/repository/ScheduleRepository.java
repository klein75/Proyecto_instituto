package com.irojas.demojwt.schedule.persistence.repository;

import java.sql.Time;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irojas.demojwt.Utilities.Enum.DayEnum;
import com.irojas.demojwt.Utilities.Enum.StateEnum;
import com.irojas.demojwt.schedule.persistence.entity.ScheduleEntity;

public interface ScheduleRepository  extends JpaRepository<ScheduleEntity, Long>{

     List<ScheduleEntity> findByDay(DayEnum day);

    List<ScheduleEntity> findByStart(Time start);

    List<ScheduleEntity> findByEnd(Time end);

    List<ScheduleEntity> findByState(StateEnum state);

    List<ScheduleEntity> findByDayAndStartAndEndAndState(DayEnum day, Time start, Time end, StateEnum state);
}
