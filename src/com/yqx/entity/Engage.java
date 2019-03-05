package com.yqx.entity;

import java.util.Date;

/*
 * 
 * Ƹ����
 * */
public class Engage {
	
	private int id;//ID
	private int empId;//Ա�����
	private String skillname;//����
	private Date createDate;//ʱ��
	
	public Engage(int id, int empId, String skillname, Date createDate) {
		super();
		this.id = id;
		this.empId = empId;
		this.skillname = skillname;
		this.createDate = createDate;
	}

	public Engage() {
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

	public String getSkillname() {
		return skillname;
	}

	public void setSkillname(String skillname) {
		this.skillname = skillname;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Engage [id=" + id + ", empId=" + empId + ", skillname=" + skillname + ", createDate=" + createDate
				+ "]";
	}
	
	
}
