package com.irojas.demojwt.reportes.Services.Interfaces;

import java.util.List;

import com.irojas.demojwt.reportes.persistence.Entity.ReportEntity;

public interface IServiceReport {
    
    List<ReportEntity> findAll();

    ReportEntity findById(Long id);

    ReportEntity save(ReportEntity report);

    ReportEntity update(Long id, ReportEntity report);

    void delete(Long id);
}
