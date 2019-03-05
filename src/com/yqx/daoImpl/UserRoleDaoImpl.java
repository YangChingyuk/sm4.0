package com.yqx.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yqx.dao.UserRoleDao;
import com.yqx.entity.UserRole;
import com.yqx.util.DBUtil;

public class UserRoleDaoImpl implements UserRoleDao{

	@Override
	public void add(UserRole userRole) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_sys_userrole(uid,rid) values(?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, userRole.getUid());
			pst.setInt(2, userRole.getRid());
			System.out.println("�ɹ�����" + pst.executeUpdate() + "������");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public void addMore(List<UserRole> list) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_sys_userrole(uid,rid) values(?,?)";
		try {
			pst = con.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				UserRole userRole = list.get(i);
				pst.setInt(1, userRole.getUid());
				pst.setInt(2, userRole.getRid());

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
		String sql = "delete from hr_sys_userrole where id=?";
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
		String sql = "delete from hr_sys_userrole where id in (" + ids + ")";
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
	public void update(UserRole userRole) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "update hr_sys_userrole set uid=?,rid=? where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, userRole.getUid());
			pst.setInt(2, userRole.getRid());
			pst.setInt(3, userRole.getId());
			System.out.println("�ɹ��޸�" + pst.executeUpdate() + "������");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public UserRole queryById(int id) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_sys_userrole where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				UserRole a = new UserRole();
				a.setId(rs.getInt("id"));
				a.setUid(rs.getInt("uid"));
				a.setRid(rs.getInt("rid"));
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
	public List<UserRole> queryAll() {
		List<UserRole> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_sys_userrole order by id";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				UserRole a = new UserRole();
				a.setId(rs.getInt("id"));
				a.setUid(rs.getInt("uid"));
				a.setRid(rs.getInt("rid"));
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
	public List<UserRole> queryByPage(int currentPage, int pageSize) {
		List<UserRole> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_sys_userrole order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage - 1) * pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while (rs.next()) {
				UserRole a = new UserRole();
				a.setId(rs.getInt("id"));
				a.setUid(rs.getInt("uid"));
				a.setRid(rs.getInt("rid"));
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
	public List<UserRole> queryByPage(int currentPage, int pageSize, String condition) {
		List<UserRole> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_sys_userrole " + condition + " order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage - 1) * pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while (rs.next()) {
				UserRole a = new UserRole();
				a.setId(rs.getInt("id"));
				a.setUid(rs.getInt("uid"));
				a.setRid(rs.getInt("rid"));
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
		String sql = "select count(*) from hr_sys_userrole";
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
		String sql = "select count(*) from hr_sys_userrole " + condition;
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
