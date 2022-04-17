package com.urs.bsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.urs.bsi.model.TplHistory;

public interface TplHistoryRepository  extends JpaRepository<TplHistory,Long>{

}
