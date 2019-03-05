package com.yqx.dao;

import java.util.List;

import com.yqx.entity.Resume;

public interface ResumeDao {

	public void add(Resume resume);

	/**
	 * ������Ӽ�¼
	 * 
	 * @param list
	 */
	public void addMore(List<Resume> list);

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
	public void update(Resume resume);

	/**
	 * ����������ѯ������¼
	 * 
	 * @param id
	 * @return
	 */
	public Resume queryById(int id);

	/**
	 * ��ѯ���м�¼
	 * 
	 * @return
	 */
	public List<Resume> queryAll();

	/**
	 * ��ҳ��ѯ��¼
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<Resume> queryByPage(int currentPage, int pageSize);

	/**
	 * ������ҳ��ѯ��¼
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<Resume> queryByPage(int currentPage, int pageSize, String condition);

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
