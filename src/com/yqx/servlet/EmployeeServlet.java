package com.yqx.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yqx.dao.DepartmentDao;
import com.yqx.dao.EmployeeDao;
import com.yqx.daoImpl.DepartmentDaoImpl;
import com.yqx.daoImpl.EmployeeDaoImpl;
import com.yqx.entity.Department;
import com.yqx.entity.Employee;

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opType = request.getParameter("opType");
		if (opType == null) {
			queryByPage(request, response);
		} else if (opType.equals("add")) {
			add(request, response);
		} else if (opType.equals("addForm")) {
			addForm(request, response);
		} else if (opType.equals("update")) {
			update(request, response);
		} else if (opType.equals("deleteById")) {
			deleteById(request, response);
		} else if (opType.equals("deleteMore")) {
			deleteMore(request, response);
		} else if (opType.equals("queryById")) {
			queryById(request, response);
		} else if (opType.equals("queryByPage")) {
			queryByPage(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void addForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DepartmentDao ddao = new DepartmentDaoImpl();
		List<Department> deptList = ddao.queryAll();
		request.setAttribute("deptList", deptList);
		request.getRequestDispatcher("/employee/add.jsp").forward(request, response);
	}

	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String edu = request.getParameter("edu");
		String degree = request.getParameter("degree");
		String job = request.getParameter("job");
		String deptId = request.getParameter("deptId");
		String state = request.getParameter("state");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		Employee e = new Employee();
		e.setName(name);
		e.setSex(Integer.parseInt(sex));
		e.setAge(Integer.parseInt(age));
		e.setEdu(Integer.parseInt(edu));
		e.setDegree(Integer.parseInt(degree));
		e.setJob(job);
		e.setDeptId(Integer.parseInt(deptId));
		e.setState(Integer.parseInt(state));
		e.setPhone(phone);
		e.setAddress(address);
		EmployeeDao edao = new EmployeeDaoImpl();
		edao.add(e);
		queryByPage(request, response);
	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String edu = request.getParameter("edu");
		String degree = request.getParameter("degree");
		String job = request.getParameter("job");
		String deptId = request.getParameter("deptId");
		String state = request.getParameter("state");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");

		Employee e = new Employee();
		e.setId(Integer.parseInt(id));
		e.setName(name);
		e.setSex(Integer.parseInt(sex));
		e.setAge(Integer.parseInt(age));
		e.setEdu(Integer.parseInt(edu));
		e.setDegree(Integer.parseInt(degree));
		e.setJob(job);
		e.setDeptId(Integer.parseInt(deptId));
		e.setState(Integer.parseInt(state));
		e.setPhone(phone);
		e.setAddress(address);
		EmployeeDao edao = new EmployeeDaoImpl();
		edao.update(e);
		queryByPage(request, response);
	}

	public void deleteById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		EmployeeDao edao = new EmployeeDaoImpl();
		edao.deleteById(Integer.parseInt(id));
		queryByPage(request, response);
	}

	public void deleteMore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ids = request.getParameter("ids");
		EmployeeDao edao = new EmployeeDaoImpl();
		edao.deleteMore(ids);
		queryByPage(request, response);
	}

	public void queryById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String qname = request.getParameter("qname");
		String qdeptId = request.getParameter("qdeptId");
		String currentPage = request.getParameter("currentPage");

		EmployeeDao edao = new EmployeeDaoImpl();
		Employee e = edao.queryById(Integer.parseInt(id));

		DepartmentDao ddao = new DepartmentDaoImpl();
		List<Department> deptList = ddao.queryAll();

		request.setAttribute("employee", e);
		request.setAttribute("deptList", deptList);

		request.setAttribute("qname", qname);
		request.setAttribute("qdeptId", qdeptId);
		request.setAttribute("currentPage", currentPage);
		request.getRequestDispatcher("/employee/update.jsp").forward(request, response);
	}

	public void queryByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String currentPage = request.getParameter("currentPage");

		String qname = request.getParameter("qname");
		String qdeptId = request.getParameter("qdeptId");

		String condition = " where 1=1 ";
		if (qname != null && !qname.equals("") && !qname.equalsIgnoreCase("null")) {
			condition += " and name like '%" + qname + "%' ";
		}
		if (qdeptId != null && !qdeptId.equals("") && !qdeptId.equalsIgnoreCase("null")) {
			condition += " and deptId =" + qdeptId + " ";
		}

		EmployeeDao edao = new EmployeeDaoImpl();

		// 当前页数
		int sp = 1;
		// 每页显示的记录数
		int pageSize = 20;
		// 总记录数
		int totals = edao.getTotals(condition);
		// 总页数
		int pageCounts = totals / pageSize;
		if (totals % pageSize != 0) {
			pageCounts++;
		}
		try {
			sp = Integer.parseInt(currentPage);
		} catch (Exception e) {
			sp = 1;
		}

		if (sp > pageCounts) {
			sp = pageCounts;
		}
		if (sp < 1) {
			sp = 1;
		}

		List<Employee> list = edao.queryByPage(sp, pageSize, condition);
		request.setAttribute("list", list);
		request.setAttribute("totals", totals);
		request.setAttribute("pageCounts", pageCounts);
		request.setAttribute("sp", sp);
		request.setAttribute("qname", qname);
		request.setAttribute("qdeptId", qdeptId);
		request.getRequestDispatcher("/employee/list.jsp").forward(request, response);
	}

}
