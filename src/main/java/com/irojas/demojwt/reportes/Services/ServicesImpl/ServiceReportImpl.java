package com.irojas.demojwt.reportes.Services.ServicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irojas.demojwt.reportes.Services.Interfaces.IServiceReport;
import com.irojas.demojwt.reportes.persistence.Entity.ReportEntity;
import com.irojas.demojwt.reportes.persistence.Respository.ReportRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ServiceReportImpl implements IServiceReport {

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public List<ReportEntity> findAll() {
        return reportRepository.findAll();
    }

    @Override
    public ReportEntity findById(Long id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reporte no encontrado con id: " + id));
    }

    @Override
    public ReportEntity save(ReportEntity report) {
        return reportRepository.save(report);
    }

    @Override
    public ReportEntity update(Long id, ReportEntity updatedReport) {
        ReportEntity existingReport = findById(id);
        existingReport.setNota(updatedReport.getNota());
        existingReport.setFallas(updatedReport.getFallas());
        existingReport.setComentarios(updatedReport.getComentarios());
        existingReport.setEstudiante(updatedReport.getEstudiante());
        existingReport.setMateria(updatedReport.getMateria());
        existingReport.setPeriodo(updatedReport.getPeriodo());
        existingReport.setEstado(updatedReport.getEstado());
        return reportRepository.save(existingReport);
    }

    @Override
    public void delete(Long id) {
        reportRepository.deleteById(id);
    }
}

