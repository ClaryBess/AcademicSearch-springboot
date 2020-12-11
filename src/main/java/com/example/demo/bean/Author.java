package com.example.demo.bean;

import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
@Document(indexName="author")
public class Author implements Serializable {
	/**
	 * 作者id
	 */
	private Long id;
	/**
	 * 作者姓名
	 */
	private String name;
	/**
	 * 作者简介
	 */
	private String remark;
	
	//setters and getters
	//toString

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}