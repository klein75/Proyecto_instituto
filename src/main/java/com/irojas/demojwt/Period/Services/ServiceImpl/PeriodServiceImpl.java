package com.irojas.demojwt.Period.Services.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irojas.demojwt.Period.Persistence.Entity.PeriodEntity;
import com.irojas.demojwt.Period.Persistence.repository.PeriodRepository;
import com.irojas.demojwt.Period.Services.Interfaces.IPeriodService;
import com.irojas.demojwt.Utilities.Enum.PeriodEnum;
import com.irojas.demojwt.Utilities.Enum.StateEnum;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PeriodServiceImpl implements IPeriodService {

    @Autowired
    private PeriodRepository periodRepository;

    @Override
    public PeriodEntity createPeriod(PeriodEntity period) {
        return periodRepository.save(period);
    }

    @Override
    public Optional<PeriodEntity> getPeriodById(Long id) {
        return periodRepository.findById(id);
    }

    @Override
    public List<PeriodEntity> getAllPeriods() {
        return periodRepository.findAll();
    }

    @Override
    public PeriodEntity updatePeriod(Long id, PeriodEntity updatedPeriod) {
        Optional<PeriodEntity> existingPeriod = periodRepository.findById(id);
        if (existingPeriod.isPresent()) {
            PeriodEntity period = existingPeriod.get();
            period.setPeriodo(updatedPeriod.getPeriodo());
            period.setInicio(updatedPeriod.getInicio());
            period.setNotas(updatedPeriod.getNotas());
            period.setFin(updatedPeriod.getFin());
            period.setDescripcion(updatedPeriod.getDescripcion());
            period.setEstado(updatedPeriod.getEstado());
            return periodRepository.save(period);
        }
        return null;
    }

    @Override
    public void deletePeriod(Long id) {
        periodRepository.deleteById(id);
    }

    // Métodos de búsqueda
    @Override
    public List<PeriodEntity> findByEstado(StateEnum estado) {
        return periodRepository.findByEstado(estado);
    }

    @Override
    public List<PeriodEntity> findByPeriodo(PeriodEnum periodo) {
        return periodRepository.findByPeriodo(periodo);
    }

    @Override
    public List<PeriodEntity> findBetweenDates(Date startDate, Date endDate) {
        return periodRepository.findByInicioBetween(startDate, endDate);
    }
}
