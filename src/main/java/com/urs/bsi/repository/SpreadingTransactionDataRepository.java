package com.urs.bsi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.urs.bsi.model.SpreadingTransactionData;

@Repository
public interface SpreadingTransactionDataRepository extends CrudRepository<SpreadingTransactionData, Long> {

	List<SpreadingTransactionData> findBySummaryLevelDataId (Long dataId);
}
