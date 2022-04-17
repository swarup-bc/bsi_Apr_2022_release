package com.urs.bsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.urs.bsi.model.URSIoJsonDetails;

@Repository
public interface URSIoJsonDetailsRepository extends JpaRepository<URSIoJsonDetails, Long> {

}
