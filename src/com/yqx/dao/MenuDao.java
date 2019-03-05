package com.yqx.dao;

import java.util.List;

import com.yqx.entity.Menu;

public interface MenuDao {
	/**
	 * ��Ӽ�¼
	 * 
	 * @param
	 */
	public void add(Menu menu);

	/**
	 * ������Ӽ�¼
	 * 
	 * @param list
	 */
	public void addMore(List<Menu> list);

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
	public void update(Menu menu);

	/**
	 * ����������ѯ������¼
	 * 
	 * @param id
	 * @return
	 */
	public Menu queryById(int id);

	/**
	 * ��ѯ���м�¼
	 * 
	 * @return
	 */
	public List<Menu> queryAll();

	/**
	 * ��ҳ��ѯ��¼
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<Menu> queryByPage(int currentPage, int pageSize);

	/**
	 * ������ҳ��ѯ��¼
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<Menu> queryByPage(int currentPage, int pageSize, String condition);

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
