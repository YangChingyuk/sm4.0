package com.yqx.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.yqx.dao.UserDao;
import com.yqx.entity.User;
import com.yqx.util.DBUtil;

public class UserDaoImpl implements UserDao{

	@Override
	public void add(User user) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_sys_user(username,password,empId,state,createTime) values(?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.setInt(3, user.getEmpId());
			pst.setInt(4, user.getState());
			pst.setTimestamp(5, new Timestamp(System.currentTimeMillis()));

			System.out.println("�ɹ�����" + pst.executeUpdate() + "������");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public void addMore(List<User> list) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_sys_user(username,password,empId,state,createTime) values(?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				User user = list.get(i);
				pst.setString(1, user.getUsername());
				pst.setString(2, user.getPassword());
				pst.setInt(3, user.getEmpId());
				pst.setInt(4, user.getState());
				pst.setTimestamp(5, new Timestamp(System.currentTimeMillis()));

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
		String sql = "delete from hr_sys_user where id=?";
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
		String sql = "delete from hr_sys_user where id in (" + ids + ")";
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
	public void update(User user) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "update hr_sys_user set username=?,password=?,empId=?,state=?,createTime=?,newjob=?,mixDate=? where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.setInt(3, user.getEmpId());
			pst.setInt(4, user.getState());
			pst.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			pst.setInt(7, user.getId());
			System.out.println("�ɹ��޸�" + pst.executeUpdate() + "������");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public User queryById(int id) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_sys_user where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				User r = new User();
				r.setId(rs.getInt("id"));
				r.setUsername(rs.getString("username"));
				r.setPassword(rs.getString("password"));
				r.setEmpId(rs.getInt("empId"));
				r.setState(rs.getInt("state"));
				r.setCreateTime(rs.getTimestamp("createTime"));
				return r;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, rs);
		}
		return null;
	}

	@Override
	public List<User> queryAll() {
		List<User> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_sys_user order by id";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				User r = new User();
				r.setId(rs.getInt("id"));
				r.setUsername(rs.getString("username"));
				r.setPassword(rs.getString("password"));
				r.setEmpId(rs.getInt("empId"));
				r.setState(rs.getInt("state"));
				r.setCreateTime(rs.getTimestamp("createTime"));
				list.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, rs);
		}
		return list;
	}

	@Override
	public List<User> queryByPage(int currentPage, int pageSize) {
		List<User> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_sys_user order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage - 1) * pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while (rs.next()) {
				User r = new User();
				r.setId(rs.getInt("id"));
				r.setUsername(rs.getString("username"));
				r.setPassword(rs.getString("password"));
				r.setEmpId(rs.getInt("empId"));
				r.setState(rs.getInt("state"));
				r.setCreateTime(rs.getTimestamp("createTime"));
				list.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, rs);
		}
		return list;
	}

	@Override
	public List<User> queryByPage(int currentPage, int pageSize, String condition) {
		List<User> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_sys_user " + condition + " order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage - 1) * pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while (rs.next()) {
				User r = new User();
				r.setId(rs.getInt("id"));
				r.setUsername(rs.getString("username"));
				r.setPassword(rs.getString("password"));
				r.setEmpId(rs.getInt("empId"));
				r.setState(rs.getInt("state"));
				r.setCreateTime(rs.getTimestamp("createTime"));
				list.add(r);
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
		String sql = "select count(*) from hr_sys_user";
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
		String sql = "select count(*) from hr_sys_user " + condition;
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
