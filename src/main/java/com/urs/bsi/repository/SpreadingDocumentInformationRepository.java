package com.urs.bsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.urs.bsi.model.SpreadingDocumentInformation;

@Repository
public interface SpreadingDocumentInformationRepository extends JpaRepository<SpreadingDocumentInformation, Long> {

}
