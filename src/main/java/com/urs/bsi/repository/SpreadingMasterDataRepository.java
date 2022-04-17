package com.urs.bsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.urs.bsi.model.SpreadingMasterData;

@Repository
public interface SpreadingMasterDataRepository extends JpaRepository<SpreadingMasterData, Long> {

	SpreadingMasterData findByHistoryId(Long historyId);
}
