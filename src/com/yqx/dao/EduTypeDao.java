package com.yqx.dao;

import java.util.List;

import com.yqx.entity.EduType;


public interface EduTypeDao {
	/**
	 * ��Ӽ�¼
	 * 
	 * @param
	 */
	public void add(EduType eduType);

	/**
	 * ������Ӽ�¼
	 * 
	 * @param list
	 */
	public void addMore(List<EduType> list);

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
	public void update(EduType eduType);

	/**
	 * ����������ѯ������¼
	 * 
	 * @param id
	 * @return
	 */
	public EduType queryById(int id);

	/**
	 * ��ѯ���м�¼
	 * 
	 * @return
	 */
	public List<EduType> queryAll();

	/**
	 * ��ҳ��ѯ��¼
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<EduType> queryByPage(int currentPage, int pageSize);

	/**
	 * ������ҳ��ѯ��¼
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<EduType> queryByPage(int currentPage, int pageSize, String condition);

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
