package com.irojas.demojwt.schedule.presentation.controller;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.irojas.demojwt.Utilities.Enum.DayEnum;
import com.irojas.demojwt.Utilities.Enum.StateEnum;
import com.irojas.demojwt.schedule.Service.Interfaces.IScheduleService;
import com.irojas.demojwt.schedule.persistence.entity.ScheduleEntity;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    private IScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleEntity> createSchedule(@RequestBody ScheduleEntity schedule) {
        ScheduleEntity createdSchedule = scheduleService.createSchedule(schedule);
        return new ResponseEntity<>(createdSchedule, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleEntity> getScheduleById(@PathVariable Long id) {
        Optional<ScheduleEntity> schedule = scheduleService.getScheduleById(id);
        return schedule.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<ScheduleEntity>> getAllSchedules() {
        List<ScheduleEntity> schedules = scheduleService.getAllSchedules();
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleEntity> updateSchedule(@PathVariable Long id, @RequestBody ScheduleEntity schedule) {
        ScheduleEntity updatedSchedule = scheduleService.updateSchedule(id, schedule);
        if (updatedSchedule != null) {
            return new ResponseEntity<>(updatedSchedule, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search/day/{day}")
    public ResponseEntity<List<ScheduleEntity>> findByDay(@PathVariable DayEnum day) {
        List<ScheduleEntity> schedules = scheduleService.findByDay(day);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @GetMapping("/search/start/{start}")
    public ResponseEntity<List<ScheduleEntity>> findByStart(@PathVariable Time start) {
        List<ScheduleEntity> schedules = scheduleService.findByStart(start);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @GetMapping("/search/end/{end}")
    public ResponseEntity<List<ScheduleEntity>> findByEnd(@PathVariable Time end) {
        List<ScheduleEntity> schedules = scheduleService.findByEnd(end);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @GetMapping("/search/status/{status}")
    public ResponseEntity<List<ScheduleEntity>> findByState(@PathVariable StateEnum state) {
        List<ScheduleEntity> schedules = scheduleService.findByState(state);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ScheduleEntity>> findByDayAndStartAndEndAndStatus(
            @RequestParam DayEnum day,
            @RequestParam Time start,
            @RequestParam Time end,
            @RequestParam StateEnum state) {
        List<ScheduleEntity> schedules = scheduleService.findByDayAndStartAndEndAndState(day, start, end, state);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }
}
