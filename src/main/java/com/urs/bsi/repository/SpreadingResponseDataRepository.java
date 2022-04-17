package com.urs.bsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.urs.bsi.model.SpreadingResponseData;

@Repository
public interface SpreadingResponseDataRepository extends JpaRepository<SpreadingResponseData, Long> {

}
