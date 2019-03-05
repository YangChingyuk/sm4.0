package com.yqx.entity;

import java.util.Date;

/*
 * Ա��֤����
 * */
public class Certificate {

	private int id;//ID
	private int empId;//Ա�����
	private int eduid;//��ѵ���
	private String name;//֤������
	private String code;//֤����
	private Date getDate;//���ʱ��
	
	public Certificate(int id, int empId, int eduid, String name, String code, Date getDate) {
		super();
		this.id = id;
		this.empId = empId;
		this.eduid = eduid;
		this.name = name;
		this.code = code;
		this.getDate = getDate;
	}

	public Certificate() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getEduid() {
		return eduid;
	}

	public void setEduid(int eduid) {
		this.eduid = eduid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getGetDate() {
		return getDate;
	}

	public void setGetDate(Date getDate) {
		this.getDate = getDate;
	}

	@Override
	public String toString() {
		return "Certificate [id=" + id + ", empId=" + empId + ", eduid=" + eduid + ", name=" + name + ", code=" + code
				+ ", getDate=" + getDate + "]";
	}
	
}
