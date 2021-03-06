package com.yqx.dao;

import java.util.List;

import com.yqx.entity.Role;


public interface RoleDao {
	/**
	 * 添加记录
	 * 
	 * @param
	 */
	public void add(Role role);

	/**
	 * 批量添加记录
	 * 
	 * @param list
	 */
	public void addMore(List<Role> list);

	/**
	 * 根据主键删除记录
	 * 
	 * @param id
	 */
	public void deleteById(int id);

	/**
	 * 根据主键批量删除记录
	 * 
	 * @param ids
	 */
	public void deleteMore(String ids);

	/**
	 * 更新记录
	 * 
	 * @param student
	 */
	public void update(Role role);

	/**
	 * 根据主键查询单条记录
	 * 
	 * @param id
	 * @return
	 */
	public Role queryById(int id);

	/**
	 * 查询所有记录
	 * 
	 * @return
	 */
	public List<Role> queryAll();

	/**
	 * 分页查询记录
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<Role> queryByPage(int currentPage, int pageSize);

	/**
	 * 条件分页查询记录
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<Role> queryByPage(int currentPage, int pageSize, String condition);

	/**
	 * 获取总记录数
	 * 
	 * @return
	 */
	public int getTotals();

	/**
	 * 根据条件获取总记录数
	 * 
	 * @return
	 */
	public int getTotals(String condition);
}
