package com.urs.bsi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.urs.bsi.model.SpreadingSummaryData;

@Repository
public interface SpreadingSummaryDataRepository extends CrudRepository<SpreadingSummaryData, Long> {

	List<SpreadingSummaryData> findBySpreadingAdataId(Long spreadingAdataId);
}
