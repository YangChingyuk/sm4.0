package com.yqx.dao;

import java.util.List;

import com.yqx.entity.Employee;

public interface EmployeeDao {

	/**
	 * ��Ӽ�¼
	 * @param 
	 */
	public void add(Employee employee);
	/**
	 * ������Ӽ�¼
	 * @param list
	 */
	public void addMore(List<Employee> list);
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
	 * @param employee
	 */
	public void update(Employee employee);
	/**
	 * ����������ѯ������¼
	 * @param id
	 * @return
	 */
	public Employee queryById(int id);
	/**
	 * ��ѯ���м�¼
	 * @return
	 */
	public List<Employee> queryAll();
	/**
	 * ��ҳ��ѯ��¼
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<Employee> queryByPage(int currentPage,int pageSize);
	/**
	 * ������ҳ��ѯ��¼
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<Employee> queryByPage(int currentPage,int pageSize,String condition);
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