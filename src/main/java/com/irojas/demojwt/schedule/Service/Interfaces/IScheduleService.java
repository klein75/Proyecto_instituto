package com.irojas.demojwt.schedule.Service.Interfaces;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

import com.irojas.demojwt.Utilities.Enum.DayEnum;
import com.irojas.demojwt.Utilities.Enum.StateEnum;
import com.irojas.demojwt.schedule.persistence.entity.ScheduleEntity;

public interface IScheduleService {

    ScheduleEntity createSchedule(ScheduleEntity schedule);

    Optional<ScheduleEntity> getScheduleById(Long id);

    List<ScheduleEntity> getAllSchedules();

    ScheduleEntity updateSchedule(Long id, ScheduleEntity schedule);

    void deleteSchedule(Long id);

    List<ScheduleEntity> findByDay(DayEnum day);

    List<ScheduleEntity> findByStart(Time start);

    List<ScheduleEntity> findByEnd(Time end);

    List<ScheduleEntity> findByState(StateEnum state);

    List<ScheduleEntity> findByDayAndStartAndEndAndState(DayEnum day, Time start, Time end, StateEnum state);
}
