package com.irojas.demojwt.Period.Persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irojas.demojwt.Period.Persistence.Entity.PeriodEntity;
import com.irojas.demojwt.Utilities.Enum.PeriodEnum;
import com.irojas.demojwt.Utilities.Enum.StateEnum;

public interface PeriodRepository extends JpaRepository<PeriodEntity, Long> {

       List<PeriodEntity> findByEstado(StateEnum estado);

    List<PeriodEntity> findByPeriodo(PeriodEnum periodo);

    List<PeriodEntity> findByInicioBetween(Date startDate, Date endDate);

    
}
