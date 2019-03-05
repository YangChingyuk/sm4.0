package com.yqx.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yqx.dao.LogDao;
import com.yqx.entity.Log;
import com.yqx.util.DBUtil;

public class LogDaoImpl implements LogDao{

	@Override
	public void add(Log log) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_sys_log(uid,loginTime,logoutTime) values(?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, log.getUid());
			pst.setDate(2, new Date(log.getLoginTime().getTime()));
			pst.setDate(3, new Date(log.getLogoutTime().getTime()));

			System.out.println("�ɹ�����" + pst.executeUpdate() + "������");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public void addMore(List<Log> list) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_sys_log(uid,loginTime,logoutTime) values(?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				Log log = list.get(i);
				pst.setInt(1, log.getUid());
				pst.setDate(2, new Date(log.getLoginTime().getTime()));
				pst.setDate(3, new Date(log.getLogoutTime().getTime()));

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
		String sql = "delete from hr_sys_log where id=?";
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
		String sql = "delete from hr_sys_log where id in (" + ids + ")";
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
	public void update(Log log) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "update hr_sys_log set uid=?,loginTime=?,logoutTime=? where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, log.getUid());
			pst.setDate(2, new Date(log.getLoginTime().getTime()));
			pst.setDate(3, new Date(log.getLogoutTime().getTime()));
			pst.setInt(4, log.getId());
			System.out.println("�ɹ��޸�" + pst.executeUpdate() + "������");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public Log queryById(int id) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_sys_log where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				Log r = new Log();
				r.setId(rs.getInt("id"));
				r.setUid(rs.getInt("uid"));
				r.setLoginTime(rs.getDate("loginTime"));
				r.setLogoutTime(rs.getDate("logoutTime"));
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
	public List<Log> queryAll() {
		List<Log> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_sys_log order by id";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Log r = new Log();
				r.setId(rs.getInt("id"));
				r.setUid(rs.getInt("uid"));
				r.setLoginTime(rs.getDate("loginTime"));
				r.setLogoutTime(rs.getDate("logoutTime"));
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
	public List<Log> queryByPage(int currentPage, int pageSize) {
		List<Log> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_sys_log order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage - 1) * pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while (rs.next()) {
				Log r = new Log();
				r.setId(rs.getInt("id"));
				r.setUid(rs.getInt("uid"));
				r.setLoginTime(rs.getDate("loginTime"));
				r.setLogoutTime(rs.getDate("logoutTime"));
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
	public List<Log> queryByPage(int currentPage, int pageSize, String condition) {
		List<Log> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_sys_log " + condition + " order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage - 1) * pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while (rs.next()) {
				Log r = new Log();
				r.setId(rs.getInt("id"));
				r.setUid(rs.getInt("uid"));
				r.setLoginTime(rs.getDate("loginTime"));
				r.setLogoutTime(rs.getDate("logoutTime"));
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
		String sql = "select count(*) from hr_sys_log";
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
		String sql = "select count(*) from hr_sys_log " + condition;
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
