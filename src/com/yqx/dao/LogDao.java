package com.yqx.dao;

import java.util.List;

import com.yqx.entity.Log;


public interface LogDao {
	/**
	 * ��Ӽ�¼
	 * @param 
	 */
	public void add(Log log);
	/**
	 * ������Ӽ�¼
	 * @param list
	 */
	public void addMore(List<Log> list);
	/**
	 * ��������ɾ����¼
	 * @param id
	 */
	public void deleteById(int id);
	/**
	 * ������������ɾ����¼
	 * @param ids
	 */
	public void deleteMore(String ids);
	/**
	 * ���¼�¼
	 * @param student
	 */
	public void update(Log log);
	/**
	 * ����������ѯ������¼
	 * @param id
	 * @return
	 */
	public Log queryById(int id);
	/**
	 * ��ѯ���м�¼
	 * @return
	 */
	public List<Log> queryAll();
	/**
	 * ��ҳ��ѯ��¼
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<Log> queryByPage(int currentPage,int pageSize);
	/**
	 * ������ҳ��ѯ��¼
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<Log> queryByPage(int currentPage,int pageSize,String condition);
	/**
	 * ��ȡ�ܼ�¼��
	 * @return
	 */
	public int getTotals();
	/**
	 * ����������ȡ�ܼ�¼��
	 * @return
	 */
	public int getTotals(String condition);
}

