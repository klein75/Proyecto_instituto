package com.irojas.demojwt.reportes.Presentation.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irojas.demojwt.reportes.Services.Interfaces.IServiceReport;
import com.irojas.demojwt.reportes.persistence.Entity.ReportEntity;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private IServiceReport serviceReport;

    @GetMapping
    public List<ReportEntity> getAllReports() {
        return serviceReport.findAll();
    }

    @GetMapping("/{id}")
    public ReportEntity getReportById(@PathVariable Long id) {
        return serviceReport.findById(id);
    }

    @PostMapping
    public ResponseEntity<ReportEntity> createReport(@RequestBody ReportEntity report) {
        ReportEntity createdReport = serviceReport.save(report);
        return new ResponseEntity<>(createdReport, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReportEntity> updateReport(@PathVariable Long id, @RequestBody ReportEntity report) {
        ReportEntity updatedReport = serviceReport.update(id, report);
        return new ResponseEntity<>(updatedReport, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        serviceReport.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}