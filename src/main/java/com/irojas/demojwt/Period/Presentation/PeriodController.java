package com.irojas.demojwt.Period.Presentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.irojas.demojwt.Period.Persistence.Entity.PeriodEntity;
import com.irojas.demojwt.Period.Services.Interfaces.IPeriodService;
import com.irojas.demojwt.Utilities.Enum.PeriodEnum;
import com.irojas.demojwt.Utilities.Enum.StateEnum;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/periods")
public class PeriodController {

    @Autowired
    private IPeriodService periodService;

    @PostMapping("/create")
    public ResponseEntity<PeriodEntity> createPeriod(@RequestBody PeriodEntity period) {
        PeriodEntity createdPeriod = periodService.createPeriod(period);
        return ResponseEntity.ok(createdPeriod);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeriodEntity> getPeriodById(@PathVariable Long id) {
        Optional<PeriodEntity> period = periodService.getPeriodById(id);
        return period.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/todos")
    public ResponseEntity<List<PeriodEntity>> getAllPeriods() {
        List<PeriodEntity> periods = periodService.getAllPeriods();
        return ResponseEntity.ok(periods);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeriodEntity> updatePeriod(@PathVariable Long id, @RequestBody PeriodEntity updatedPeriod) {
        PeriodEntity period = periodService.updatePeriod(id, updatedPeriod);
        if (period != null) {
            return ResponseEntity.ok(period);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePeriod(@PathVariable Long id) {
        periodService.deletePeriod(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar por estado
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<PeriodEntity>> getPeriodsByEstado(@PathVariable StateEnum estado) {
        List<PeriodEntity> periods = periodService.findByEstado(estado);
        return ResponseEntity.ok(periods);
    }

    // Buscar por periodo
    @GetMapping("/periodo/{periodo}")
    public ResponseEntity<List<PeriodEntity>> getPeriodsByPeriodo(@PathVariable PeriodEnum periodo) {
        List<PeriodEntity> periods = periodService.findByPeriodo(periodo);
        return ResponseEntity.ok(periods);
    }

    // Buscar entre fechas
    @GetMapping("/between")
    public ResponseEntity<List<PeriodEntity>> getPeriodsBetweenDates(@RequestParam("startDate") Date startDate,
                                                                     @RequestParam("endDate") Date endDate) {
        List<PeriodEntity> periods = periodService.findBetweenDates(startDate, endDate);
        return ResponseEntity.ok(periods);
    }
}
