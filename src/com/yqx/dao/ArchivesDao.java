package com.yqx.dao;

import java.util.List;

import com.yqx.entity.Archives;

public interface ArchivesDao {
	
	/**
	 * ��Ӽ�¼
	 * @param 
	 */
	public void add(Archives archives);
	/**
	 * ������Ӽ�¼
	 * @param list
	 */
	public void addMore(List<Archives> list);
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
	public void update(Archives archives);
	/**
	 * ����������ѯ������¼
	 * @param id
	 * @return
	 */
	public Archives queryById(int id);
	/**
	 * ��ѯ���м�¼
	 * @return
	 */
	public List<Archives> queryAll();
	/**
	 * ��ҳ��ѯ��¼
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<Archives> queryByPage(int currentPage,int pageSize);
	/**
	 * ������ҳ��ѯ��¼
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<Archives> queryByPage(int currentPage,int pageSize,String condition);
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
