package com.urs.bsi.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.urs.bsi.model.BaseEntity;

@Component
public class JpaBatchInsert {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Value("${hibernate.jdbc.batch_size}")
	private int batchSize;

	public <T extends BaseEntity> Collection<T> bulkSave(Collection<T> entities) throws ServiceException {
	  final List<T> savedEntities = new ArrayList<T>(entities.size());
	  int i = 0;
	  for (T t : entities) {
	    savedEntities.add(persistOrMerge(t));
	    i++;
	    if (i % batchSize == 0) {
	      // Flush a batch of inserts and release memory.
	      entityManager.flush();
	      entityManager.clear();
	    }
	  }
	  return savedEntities;
	}

	private <T extends BaseEntity> T persistOrMerge(T t) {
	  if (t.getId() == null) {
	    entityManager.persist(t);
	    return t;
	  } else {
	    return entityManager.merge(t);
	  }
	}

}
