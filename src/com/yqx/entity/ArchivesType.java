package com.yqx.entity;

/*
 * 
 * ����������
 * */
public class ArchivesType {
	private int id;//��������id
	private String name;//��������
	private String remark;//��ע˵��
	
	public ArchivesType(int id, String name, String remark) {
		super();
		this.id = id;
		this.name = name;
		this.remark = remark;
	}
	public ArchivesType() {
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "ArchivesType [id=" + id + ", name=" + name + ", remark=" + remark + "]";
	}
	
}
