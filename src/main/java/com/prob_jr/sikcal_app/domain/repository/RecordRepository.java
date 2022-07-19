package com.prob_jr.sikcal_app.domain.repository;

import com.prob_jr.sikcal_app.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface RecordRepository extends JpaRepository<Record, Long> {

    List<Record> findAllByRecordDateEquals(@Param("yesterday") LocalDate yesterday);
}
