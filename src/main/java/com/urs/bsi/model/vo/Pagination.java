package com.urs.bsi.model.vo;

public class Pagination implements BaseVo {

	public Pagination(int offset, int limit) {
		super();
		this.offset = offset;
		this.limit = limit;
	}
	private int offset;
	private int limit;
	private Long totalCount;
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
}
