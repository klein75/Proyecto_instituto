package com.irojas.demojwt.schedule.Service.servicesImpl;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irojas.demojwt.Utilities.Enum.DayEnum;
import com.irojas.demojwt.Utilities.Enum.StateEnum;
import com.irojas.demojwt.schedule.Service.Interfaces.IScheduleService;
import com.irojas.demojwt.schedule.persistence.entity.ScheduleEntity;
import com.irojas.demojwt.schedule.persistence.repository.ScheduleRepository;

@Service
public class ScheduleServiceImpl implements IScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public ScheduleEntity createSchedule(ScheduleEntity schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public Optional<ScheduleEntity> getScheduleById(Long id) {
        return scheduleRepository.findById(id);
    }

    @Override
    public List<ScheduleEntity> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public ScheduleEntity updateSchedule(Long id, ScheduleEntity schedule) {
        Optional<ScheduleEntity> existingSchedule = scheduleRepository.findById(id);
        if (existingSchedule.isPresent()) {
            ScheduleEntity updatedSchedule = existingSchedule.get();
            updatedSchedule.setDay(schedule.getDay());
            updatedSchedule.setStart(schedule.getStart());
            updatedSchedule.setEnd(schedule.getEnd());
            updatedSchedule.setDescription(schedule.getDescription());
            updatedSchedule.setState(schedule.getState());
            return scheduleRepository.save(updatedSchedule);
        }
        return null;
    }

    @Override
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }

    @Override
    public List<ScheduleEntity> findByDay(DayEnum day) {
        return scheduleRepository.findByDay(day);
    }

    @Override
    public List<ScheduleEntity> findByStart(Time start) {
        return scheduleRepository.findByStart(start);
    }

    @Override
    public List<ScheduleEntity> findByEnd(Time end) {
        return scheduleRepository.findByEnd(end);
    }

    @Override
    public List<ScheduleEntity> findByState(StateEnum state) {
        return scheduleRepository.findByState(state);
    }

    @Override
    public List<ScheduleEntity> findByDayAndStartAndEndAndState(DayEnum day, Time start, Time end, StateEnum state) {
        return scheduleRepository.findByDayAndStartAndEndAndState(day, start, end, state);
    }
}
