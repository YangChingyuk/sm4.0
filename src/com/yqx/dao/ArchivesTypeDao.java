package com.yqx.dao;

import java.util.List;

import com.yqx.entity.ArchivesType;

public interface ArchivesTypeDao {
	/**
	 * ��Ӽ�¼
	 * 
	 * @param
	 */
	public void add(ArchivesType archivesType);

	/**
	 * ������Ӽ�¼
	 * 
	 * @param list
	 */
	public void addMore(List<ArchivesType> list);

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
	public void update(ArchivesType archivesType);

	/**
	 * ����������ѯ������¼
	 * 
	 * @param id
	 * @return
	 */
	public ArchivesType queryById(int id);

	/**
	 * ��ѯ���м�¼
	 * 
	 * @return
	 */
	public List<ArchivesType> queryAll();

	/**
	 * ��ҳ��ѯ��¼
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<ArchivesType> queryByPage(int currentPage, int pageSize);

	/**
	 * ������ҳ��ѯ��¼
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<ArchivesType> queryByPage(int currentPage, int pageSize, String condition);

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
