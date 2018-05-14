package com.born.facade.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="tb_company")
public class Company{

	@Column(name="companyname")
	private String companyName;//公司名称
	
	@Column(name="id")
	private String id;//公司ID
	
}
