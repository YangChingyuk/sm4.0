package com.yqx.entity;

import java.sql.Timestamp;

/*
 * ������
 * */
public class Archives {
	
	private int id; //����id
	private int empId;//Ա�����
	private String code;//�������
	private String name;//��������
	private String content;//��������
	private int type;//��������
	private String remark;//��ע˵��
	private Timestamp createTime;//����ʱ��
	
	public Archives(int id, int empId, String code, String name, String content, int type, String remark,
			Timestamp createTime) {
		super();
		this.id = id;
		this.empId = empId;
		this.code = code;
		this.name = name;
		this.content = content;
		this.type = type;
		this.remark = remark;
		this.createTime = createTime;
	}
	public Archives() {
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Archives [id=" + id + ", empId=" + empId + ", code=" + code + ", name=" + name + ", content=" + content
				+ ", type=" + type + ", remark=" + remark + ", createTime=" + createTime + "]";
	}
	
}
