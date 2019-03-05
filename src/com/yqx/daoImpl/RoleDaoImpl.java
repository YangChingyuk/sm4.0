package com.yqx.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yqx.dao.RoleDao;
import com.yqx.entity.Role;
import com.yqx.util.DBUtil;

public class RoleDaoImpl implements RoleDao{

	@Override
	public void add(Role role) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_sys_role(name,remark) values(?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, role.getName());
			pst.setString(2, role.getRemark());
			System.out.println("�ɹ�����" + pst.executeUpdate() + "������");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public void addMore(List<Role> list) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_sys_role(name,remark) values(?,?)";
		try {
			pst = con.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				Role role = list.get(i);
				pst.setString(1, role.getName());
				pst.setString(2, role.getRemark());

				pst.addBatch();
				if (i % 300 == 0) {
					pst.executeBatch();
					pst.clearBatch();
				}
			}
			pst.executeBatch();
			pst.clearBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public void deleteById(int id) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "delete from hr_sys_role where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			System.out.println("�ɹ�ɾ��" + pst.executeUpdate() + "������");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public void deleteMore(String ids) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "delete from hr_sys_role where id in (" + ids + ")";
		try {
			pst = con.prepareStatement(sql);
			System.out.println("�ɹ�ɾ��" + pst.executeUpdate() + "������");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public void update(Role role) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "update hr_sys_role set name=?,remark=? where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, role.getName());
			pst.setString(2, role.getRemark());
			pst.setInt(3, role.getId());
			System.out.println("�ɹ��޸�" + pst.executeUpdate() + "������");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public Role queryById(int id) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_sys_role where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				Role a = new Role();
				a.setId(rs.getInt("id"));
				a.setName(rs.getString("name"));
				a.setRemark(rs.getString("remark"));
				return a;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, rs);
		}
		return null;
	}

	@Override
	public List<Role> queryAll() {
		List<Role> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_sys_role order by id";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Role a = new Role();
				a.setId(rs.getInt("id"));
				a.setName(rs.getString("name"));
				a.setRemark(rs.getString("remark"));
				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, rs);
		}
		return list;
	}

	@Override
	public List<Role> queryByPage(int currentPage, int pageSize) {
		List<Role> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_sys_role order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage - 1) * pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while (rs.next()) {
				Role a = new Role();
				a.setId(rs.getInt("id"));
				a.setName(rs.getString("name"));
				a.setRemark(rs.getString("remark"));
				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, rs);
		}
		return list;
	}

	@Override
	public List<Role> queryByPage(int currentPage, int pageSize, String condition) {
		List<Role> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_sys_role " + condition + " order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage - 1) * pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while (rs.next()) {
				Role a = new Role();
				a.setId(rs.getInt("id"));
				a.setName(rs.getString("name"));
				a.setRemark(rs.getString("remark"));
				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, rs);
		}
		return list;
	}

	@Override
	public int getTotals() {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select count(*) from hr_sys_role";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, rs);
		}
		return 0;
	}

	@Override
	public int getTotals(String condition) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select count(*) from hr_sys_role " + condition;
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, rs);
		}
		return 0;
	}
	
}
