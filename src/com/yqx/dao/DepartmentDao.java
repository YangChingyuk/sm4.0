package com.yqx.dao;

import java.util.List;

import com.yqx.entity.Department;

public interface DepartmentDao {

	/**
	 * ��Ӽ�¼
	 * @param 
	 */
	public void add(Department department);
	/**
	 * ������Ӽ�¼
	 * @param list
	 */
	public void addMore(List<Department> list);
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
	public void update(Department department);
	/**
	 * ����������ѯ������¼
	 * @param id
	 * @return
	 */
	public Department queryById(int id);
	/**
	 * ��ѯ���м�¼
	 * @return
	 */
	public List<Department> queryAll();
	/**
	 * ��ҳ��ѯ��¼
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<Department> queryByPage(int currentPage,int pageSize);
	/**
	 * ������ҳ��ѯ��¼
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<Department> queryByPage(int currentPage,int pageSize,String condition);
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
