package com.prob_jr.sikcal_app.domain.repository;

import com.prob_jr.sikcal_app.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RecordRepository extends JpaRepository<Record, Long> {

//    private final EntityManager em;
//
//    public void save(Record record) {
//        em.persist(record);
//    }
//
//    public Record findOne(Long id) {
//        return em.find(Record.class, id);
//    }
//
//    public void delete(Record record) {
//        em.remove(record);
//    }
}
