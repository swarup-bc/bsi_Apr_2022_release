package com.urs.bsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.urs.bsi.model.SpreadingResponseFailure;

@Repository
public interface SpreadingResponseFailureRepository extends JpaRepository<SpreadingResponseFailure, Long> {

}
