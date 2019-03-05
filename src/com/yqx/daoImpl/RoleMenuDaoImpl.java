package com.yqx.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yqx.dao.RoleMenuDao;
import com.yqx.entity.RoleMenu;
import com.yqx.util.DBUtil;

public class RoleMenuDaoImpl implements RoleMenuDao{

	@Override
	public void add(RoleMenu roleMenu) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_sys_rolemenu(rid,mid) values(?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, roleMenu.getRid());
			pst.setInt(2, roleMenu.getMid());
			System.out.println("成功新增" + pst.executeUpdate() + "条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public void addMore(List<RoleMenu> list) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_sys_rolemenu(rid,mid) values(?,?)";
		try {
			pst = con.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				RoleMenu roleMenu = list.get(i);
				pst.setInt(1, roleMenu.getRid());
				pst.setInt(2, roleMenu.getMid());

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
		String sql = "delete from hr_sys_rolemenu where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			System.out.println("成功删除" + pst.executeUpdate() + "条数据");
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
		String sql = "delete from hr_sys_rolemenu where id in (" + ids + ")";
		try {
			pst = con.prepareStatement(sql);
			System.out.println("成功删除" + pst.executeUpdate() + "条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public void update(RoleMenu roleMenu) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "update hr_sys_rolemenu set rid=?,mid=? where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, roleMenu.getRid());
			pst.setInt(2, roleMenu.getMid());
			pst.setInt(3, roleMenu.getId());
			System.out.println("成功修改" + pst.executeUpdate() + "条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public RoleMenu queryById(int id) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_sys_rolemenu where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				RoleMenu a = new RoleMenu();
				a.setId(rs.getInt("id"));
				a.setRid(rs.getInt("rid"));
				a.setMid(rs.getInt("mid"));
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
	public List<RoleMenu> queryAll() {
		List<RoleMenu> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_sys_rolemenu order by id";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				RoleMenu a = new RoleMenu();
				a.setId(rs.getInt("id"));
				a.setRid(rs.getInt("rid"));
				a.setMid(rs.getInt("mid"));
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
	public List<RoleMenu> queryByPage(int currentPage, int pageSize) {
		List<RoleMenu> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_sys_rolemenu order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage - 1) * pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while (rs.next()) {
				RoleMenu a = new RoleMenu();
				a.setId(rs.getInt("id"));
				a.setRid(rs.getInt("rid"));
				a.setMid(rs.getInt("mid"));
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
	public List<RoleMenu> queryByPage(int currentPage, int pageSize, String condition) {
		List<RoleMenu> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_sys_rolemenu " + condition + " order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage - 1) * pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while (rs.next()) {
				RoleMenu a = new RoleMenu();
				a.setId(rs.getInt("id"));
				a.setRid(rs.getInt("rid"));
				a.setMid(rs.getInt("mid"));
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
		String sql = "select count(*) from hr_sys_rolemenu";
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
		String sql = "select count(*) from hr_sys_rolemenu " + condition;
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
