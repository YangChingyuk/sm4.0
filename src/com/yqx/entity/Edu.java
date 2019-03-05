package com.yqx.entity;

import java.util.Date;

/*
 *  ��ѵ������
 * */
public class Edu {
	
	private int id;//��ѵID
	private String name;//��ѵ����
	private Date beginDate;//��ʼʱ��
	private Date endDate;//����ʱ��
	private String address;//��ѵ��ַ
	private int type;//��ѵ����
	
	public Edu(int id, String name, Date beginDate, Date endDate, String address, int type) {
		super();
		this.id = id;
		this.name = name;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.address = address;
		this.type = type;
	}
	public Edu() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Edu [id=" + id + ", name=" + name + ", beginDate=" + beginDate + ", endDate=" + endDate + ", address="
				+ address + ", type=" + type + "]";
	}
	
	
}
