package com.yqx.entity;

import java.util.Date;

/*
 * ��־��
 * */
public class Log {
	
	private int id;//��־ID
	private int uid;//�û����
	private Date loginTime;//����ʱ��
	private Date logoutTime;//�ǳ�ʱ��
	
	public Log(int id, int uid, Date loginTime, Date logoutTime) {
		super();
		this.id = id;
		this.uid = uid;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
	}

	public Log() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", uid=" + uid + ", loginTime=" + loginTime + ", logoutTime=" + logoutTime + "]";
	}
	
}
