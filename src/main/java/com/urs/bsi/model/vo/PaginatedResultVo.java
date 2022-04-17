package com.urs.bsi.model.vo;

import java.util.List;

public class PaginatedResultVo<T> {
	
	private List<T> data;
	
	private Pagination pagination;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
}
