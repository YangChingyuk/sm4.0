package com.yqx.entity;

/*
 * 
 * ��ѵ�����
 * */
public class EduType {
	
	private int id;//��ѵID
	private String name;//��ѵ����
	
	public EduType(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public EduType() {
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
	@Override
	public String toString() {
		return "EduType [id=" + id + ", name=" + name + "]";
	}
	
	
}
