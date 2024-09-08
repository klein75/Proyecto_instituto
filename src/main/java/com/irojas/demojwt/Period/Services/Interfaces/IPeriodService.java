package com.irojas.demojwt.Period.Services.Interfaces;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.irojas.demojwt.Period.Persistence.Entity.PeriodEntity;
import com.irojas.demojwt.Utilities.Enum.PeriodEnum;
import com.irojas.demojwt.Utilities.Enum.StateEnum;

public interface IPeriodService {

    PeriodEntity createPeriod(PeriodEntity period);

    Optional<PeriodEntity> getPeriodById(Long id);

    List<PeriodEntity> getAllPeriods();

    PeriodEntity updatePeriod(Long id, PeriodEntity updatedPeriod);

    void deletePeriod(Long id);

    // Métodos de búsqueda
    List<PeriodEntity> findByEstado(StateEnum estado);

    List<PeriodEntity> findByPeriodo(PeriodEnum periodo);

    List<PeriodEntity> findBetweenDates(Date startDate, Date endDate);
}
