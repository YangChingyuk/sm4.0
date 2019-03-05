package com.yqx.dao;

import java.util.List;

import com.yqx.entity.Student;

public interface StudentDao {

	/**
	 * 添加记录
	 * @param student
	 */
	public void add(Student student);
	/**
	 * 批量添加记录
	 * @param list
	 */
	public void addMore(List<Student> list);
	/**
	 * 根据主键删除记录
	 * @param id
	 */
	public void deleteById(int id);
	/**
	 * 根据主键批量删除记录
	 * @param ids
	 */
	public void deleteMore(String ids);
	/**
	 * 更新记录
	 * @param student
	 */
	public void update(Student student);
	/**
	 * 根据主键查询单条记录
	 * @param id
	 * @return
	 */
	public Student queryById(int id);
	/**
	 * 查询所有记录
	 * @return
	 */
	public List<Student> queryAll();
	/**
	 * 分页查询记录
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<Student> queryByPage(int currentPage,int pageSize);
	/**
	 * 条件分页查询记录
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<Student> queryByPage(int currentPage,int pageSize,String condition);
	/**
	 * 获取总记录数
	 * @return
	 */
	public int getTotals();
	/**
	 * 根据条件获取总记录数
	 * @return
	 */
	public int getTotals(String condition);
}
