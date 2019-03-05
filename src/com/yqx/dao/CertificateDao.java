package com.yqx.dao;

import java.util.List;

import com.yqx.entity.Certificate;


public interface CertificateDao {
	/**
	 * ��Ӽ�¼
	 * @param 
	 */
	public void add(Certificate certificate);
	/**
	 * ������Ӽ�¼
	 * @param list
	 */
	public void addMore(List<Certificate> list);
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
	public void update(Certificate certificate);
	/**
	 * ����������ѯ������¼
	 * @param id
	 * @return
	 */
	public Certificate queryById(int id);
	/**
	 * ��ѯ���м�¼
	 * @return
	 */
	public List<Certificate> queryAll();
	/**
	 * ��ҳ��ѯ��¼
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<Certificate> queryByPage(int currentPage,int pageSize);
	/**
	 * ������ҳ��ѯ��¼
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<Certificate> queryByPage(int currentPage,int pageSize,String condition);
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

