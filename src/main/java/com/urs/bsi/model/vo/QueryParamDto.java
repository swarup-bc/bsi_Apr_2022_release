package com.urs.bsi.model.vo;

import java.util.HashMap;
import java.util.Map;

public class QueryParamDto<T> {
	
	public QueryParamDto() {
		
	}
	
	public QueryParamDto(String queryKey, Class<T> clazz) {
		super();
		this.queryKey = queryKey;
		this.clazz = clazz;
	}

	private String queryKey;
	
	private Class<T> clazz;
	
	private Map<String, Object> parameterMap = new HashMap<String, Object>();
	
	private PaginatedResultVo<T> paginatedResultVo = new PaginatedResultVo<T>();
	
	private boolean queryForListOfEntity;
	
	private boolean secondaryDataSource;

	public String getQueryKey() {
		return queryKey;
	}

	public void setQueryKey(String queryKey) {
		this.queryKey = queryKey;
	}

	public Class<T> getClazz() {
		return clazz;
	}

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	public PaginatedResultVo<T> getPaginatedResultVo() {
		return paginatedResultVo;
	}

	public void setPaginatedResultVo(PaginatedResultVo<T> paginatedResultVo) {
		this.paginatedResultVo = paginatedResultVo;
	}

	public Map<String, Object> getParameterMap() {
		return parameterMap;
	}

	public void setParameterMap(Map<String, Object> parameterMap) {
		this.parameterMap = parameterMap;
	}

	public boolean isQueryForListOfEntity() {
		return queryForListOfEntity;
	}

	public void setQueryForListOfEntity(boolean queryForListOfEntity) {
		this.queryForListOfEntity = queryForListOfEntity;
	}

	public boolean isSecondaryDataSource() {
		return secondaryDataSource;
	}

	public void setSecondaryDataSource(boolean secondaryDataSource) {
		this.secondaryDataSource = secondaryDataSource;
	}

}
