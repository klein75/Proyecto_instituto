package com.irojas.demojwt.reportes.persistence.Respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irojas.demojwt.reportes.persistence.Entity.ReportEntity;

public interface ReportRepository extends JpaRepository<ReportEntity, Long> {

}
