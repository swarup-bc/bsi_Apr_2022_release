package com.urs.bsi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_tpl_history")
public class TplHistory {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id	;

	private long refSrdId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRefSrdId() {
		return refSrdId;
	}

	public void setRefSrdId(long refSrdId) {
		this.refSrdId = refSrdId;
	}


}