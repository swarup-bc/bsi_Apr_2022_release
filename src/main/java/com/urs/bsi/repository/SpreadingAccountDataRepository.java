package com.urs.bsi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.urs.bsi.model.SpreadingAccountData;
@Repository
public interface SpreadingAccountDataRepository extends CrudRepository<SpreadingAccountData, Long> {

	List<SpreadingAccountData> findByHistoryId(Long historyId);
}
