package com.urs.bsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.urs.bsi.model.SpreadingRequestData;

@Repository
public interface SpreadingRequestDataRepository extends JpaRepository<SpreadingRequestData, Long> {

	@Query("SELECT s FROM SpreadingRequestData s WHERE s.process_control_number = :pcn")
	SpreadingRequestData findByprocess_control_number(@Param("pcn") String pcn);
	
}
