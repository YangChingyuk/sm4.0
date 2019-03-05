package com.yqx.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.yqx.dao.StudentDao;
import com.yqx.entity.Student;
import com.yqx.util.DBUtil;

public class StudentDaoImpl implements StudentDao{

	@Override
	public void add(Student student) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into student(name,username,password,sex,age,birthday,creatTime) values(?,?,?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, student.getName());
			pst.setString(2, student.getUsername());
			pst.setString(3, student.getPassword());
			pst.setInt(4, student.getSex());
			pst.setInt(5, student.getAge());
			pst.setDate(6, new Date(student.getBirthday().getTime()));
			pst.setTimestamp(7,new Timestamp(System.currentTimeMillis()));
			System.out.println("成功新增"+pst.executeUpdate()+"条数据");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, null);
		}
	}

	@Override
	public void addMore(List<Student> list) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into student(name,username,password,sex,age,birthday,creatTime) values(?,?,?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			for(int i=0;i<list.size();i++) {
				Student student = list.get(i);
				pst.setString(1, student.getName());
				pst.setString(2, student.getUsername());
				pst.setString(3, student.getPassword());
				pst.setInt(4, student.getSex());
				pst.setInt(5, student.getAge());
				pst.setDate(6, new Date(student.getBirthday().getTime()));
				pst.setTimestamp(7,new Timestamp(System.currentTimeMillis()));
				pst.addBatch();
				if(i%300==0) {
					pst.executeBatch();
					pst.clearBatch();
				}
			}
			pst.executeBatch();
			pst.clearBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, null);
		}
	}

	@Override
	public void deleteById(int id) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "delete from student where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			System.out.println("成功删除"+pst.executeUpdate()+"条数据");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, null);
		}
	}

	@Override
	public void deleteMore(String ids) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "delete from student where id in ("+ids+")";
		try {
			pst = con.prepareStatement(sql);
			System.out.println("成功删除"+pst.executeUpdate()+"条数据");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, null);
		}
	}

	@Override
	public void update(Student student) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "update student set name=?,username=?,password=?,sex=?,age=?,birthday=?,creatTime=? where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, student.getName());
			pst.setString(2, student.getUsername());
			pst.setString(3, student.getPassword());
			pst.setInt(4, student.getSex());
			pst.setInt(5, student.getAge());
			pst.setDate(6, new Date(student.getBirthday().getTime()));
			pst.setTimestamp(7,new Timestamp(System.currentTimeMillis()));
			pst.setInt(8, student.getId());
			System.out.println("成功修改"+pst.executeUpdate()+"条数据");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, null);
		}
	}

	@Override
	public Student queryById(int id) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from student where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setUsername(rs.getString("username"));
				s.setPassword(rs.getString("password"));
				s.setSex(rs.getInt("sex"));
				s.setAge(rs.getInt("age"));
				s.setBirthday(rs.getDate("birthday"));
				s.setCreatTime(rs.getTimestamp("creatTime"));
				return s;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, rs);
		}
		return null;
	}

	@Override
	public List<Student> queryAll() {
		List<Student> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from student order by id";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setUsername(rs.getString("username"));
				s.setPassword(rs.getString("password"));
				s.setSex(rs.getInt("sex"));
				s.setAge(rs.getInt("age"));
				s.setBirthday(rs.getDate("birthday"));
				s.setCreatTime(rs.getTimestamp("creatTime"));
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, rs);
		}
		return list;
	}

	@Override
	public List<Student> queryByPage(int currentPage, int pageSize) {
		List<Student> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from student order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setUsername(rs.getString("username"));
				s.setPassword(rs.getString("password"));
				s.setSex(rs.getInt("sex"));
				s.setAge(rs.getInt("age"));
				s.setBirthday(rs.getDate("birthday"));
				s.setCreatTime(rs.getTimestamp("creatTime"));
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, rs);
		}
		return list;
	}

	@Override
	public int getTotals() {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select count(*) from student";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, rs);
		}
		return 0;
	}
	@Override
	public int getTotals(String condition) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select count(*) from student "+condition;
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, rs);
		}
		return 0;
	}

	@Override
	public List<Student> queryByPage(int currentPage, int pageSize, String condition) {
		List<Student> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from student "+condition+" order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setUsername(rs.getString("username"));
				s.setPassword(rs.getString("password"));
				s.setSex(rs.getInt("sex"));
				s.setAge(rs.getInt("age"));
				s.setBirthday(rs.getDate("birthday"));
				s.setCreatTime(rs.getTimestamp("creatTime"));
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, rs);
		}
		return list;
	}

}
