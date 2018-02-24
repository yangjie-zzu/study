package com.blog.entity;

public class QueryCondition {
	
	private Integer id;
	
	private Integer PublisherId;
	
	private String typeName;
	
	private Integer pageNum;
	
	private Integer showNum;
	
	private String order;

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPublisherId() {
		return PublisherId;
	}

	public void setPublisherId(Integer publisherId) {
		PublisherId = publisherId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getShowNum() {
		return showNum;
	}

	public void setShowNum(Integer showNum) {
		this.showNum = showNum;
	}
	
	

}
