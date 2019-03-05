package com.yqx.dao;

import java.util.List;

import com.yqx.entity.Deploy;

public interface DeployDao {

	/**
	 * ��Ӽ�¼
	 * 
	 * @param
	 */
	public void add(Deploy deploy);

	/**
	 * ������Ӽ�¼
	 * 
	 * @param list
	 */
	public void addMore(List<Deploy> list);

	/**
	 * ��������ɾ����¼
	 * 
	 * @param id
	 */
	public void deleteById(int id);

	/**
	 * ������������ɾ����¼
	 * 
	 * @param ids
	 */
	public void deleteMore(String ids);

	/**
	 * ���¼�¼
	 * 
	 * @param student
	 */
	public void update(Deploy deploy);

	/**
	 * ����������ѯ������¼
	 * 
	 * @param id
	 * @return
	 */
	public Deploy queryById(int id);

	/**
	 * ��ѯ���м�¼
	 * 
	 * @return
	 */
	public List<Deploy> queryAll();

	/**
	 * ��ҳ��ѯ��¼
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<Deploy> queryByPage(int currentPage, int pageSize);

	/**
	 * ������ҳ��ѯ��¼
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<Deploy> queryByPage(int currentPage, int pageSize, String condition);

	/**
	 * ��ȡ�ܼ�¼��
	 * 
	 * @return
	 */
	public int getTotals();

	/**
	 * ����������ȡ�ܼ�¼��
	 * 
	 * @return
	 */
	public int getTotals(String condition);
}
