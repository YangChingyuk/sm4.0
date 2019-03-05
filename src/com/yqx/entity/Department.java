package com.yqx.entity;

/*
 * ������
 * 
 * */
public class Department {

	private int id; // �������
	private String name;// ��������
	private String manager;// ���Ÿ�����
	private String officeNo;// ���Ű칫�ұ��
	private String phone;// ���ŵ绰
	private String remark;// ��ע˵��

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

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getOfficeNo() {
		return officeNo;
	}

	public void setOfficeNo(String officeNo) {
		this.officeNo = officeNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Department() {
		super();
	}

	public Department(int id, String name, String manager, String officeNo, String phone, String remark) {
		super();
		this.id = id;
		this.name = name;
		this.manager = manager;
		this.officeNo = officeNo;
		this.phone = phone;
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", manager=" + manager + ", officeNo=" + officeNo
				+ ", phone=" + phone + ", remark=" + remark + "]";
	}

}
