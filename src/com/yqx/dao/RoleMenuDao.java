package com.yqx.dao;

import java.util.List;

import com.yqx.entity.RoleMenu;


public interface RoleMenuDao {
	/**
	 * ��Ӽ�¼
	 * @param 
	 */
	public void add(RoleMenu roleMenu);
	/**
	 * ������Ӽ�¼
	 * @param list
	 */
	public void addMore(List<RoleMenu> list);
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
	public void update(RoleMenu roleMenu);
	/**
	 * ����������ѯ������¼
	 * @param id
	 * @return
	 */
	public RoleMenu queryById(int id);
	/**
	 * ��ѯ���м�¼
	 * @return
	 */
	public List<RoleMenu> queryAll();
	/**
	 * ��ҳ��ѯ��¼
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<RoleMenu> queryByPage(int currentPage,int pageSize);
	/**
	 * ������ҳ��ѯ��¼
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<RoleMenu> queryByPage(int currentPage,int pageSize,String condition);
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
