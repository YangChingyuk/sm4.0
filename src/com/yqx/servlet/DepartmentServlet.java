package com.yqx.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yqx.dao.DepartmentDao;
import com.yqx.daoImpl.DepartmentDaoImpl;
import com.yqx.entity.Department;

@WebServlet("/DepartmentServlet")
public class DepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opType = request.getParameter("opType");
		if (opType == null) {
			queryByPage(request, response);
		} else if (opType.equals("add")) {
			add(request, response);
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

	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String manager = request.getParameter("manager");
		String officeNo = request.getParameter("officeNo");
		String phone = request.getParameter("phone");
		String remark = request.getParameter("remark");
		Department d = new Department();
		d.setName(name);
		d.setManager(manager);
		d.setOfficeNo(officeNo);
		d.setPhone(phone);
		d.setRemark(remark);
		DepartmentDao ddao = new DepartmentDaoImpl();
		ddao.add(d);
		queryByPage(request, response);
	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String manager = request.getParameter("manager");
		String officeNo = request.getParameter("officeNo");
		String phone = request.getParameter("phone");
		String remark = request.getParameter("remark");
		Department d = new Department();
		d.setId(Integer.parseInt(id));
		d.setName(name);
		d.setManager(manager);
		d.setOfficeNo(officeNo);
		d.setPhone(phone);
		d.setRemark(remark);
		DepartmentDao ddao = new DepartmentDaoImpl();
		ddao.update(d);
		queryByPage(request, response);
	}

	public void deleteById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		DepartmentDao ddao = new DepartmentDaoImpl();
		ddao.deleteById(Integer.parseInt(id));
		queryByPage(request, response);
	}

	public void deleteMore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ids = request.getParameter("ids");
		DepartmentDao ddao = new DepartmentDaoImpl();
		ddao.deleteMore(ids);
		queryByPage(request, response);
	}

	public void queryById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String currentPage = request.getParameter("currentPage");
		String qname = request.getParameter("qname");
		DepartmentDao ddao = new DepartmentDaoImpl();
		Department d = ddao.queryById(Integer.parseInt(id));
		request.setAttribute("department", d);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("qname", qname);
		request.getRequestDispatcher("/department/update.jsp").forward(request, response);
		
	}

	public void queryByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String currentPage = request.getParameter("currentPage");
		String qname = request.getParameter("qname");

		String condition = " where 1=1 ";
		if (qname != null && !qname.equals("") && !qname.equalsIgnoreCase("null")) {
			condition += " and name like '%" + qname + "%' ";
		}

		DepartmentDao ddao = new DepartmentDaoImpl();

		// 当前页数
		int sp = 1;
		// 每页显示的记录数
		int pageSize = 20;
		// 总记录数
		int totals = ddao.getTotals(condition);
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

		List<Department> list = ddao.queryByPage(sp, pageSize, condition);
		request.setAttribute("list", list);
		request.setAttribute("totals", totals);
		request.setAttribute("pageCounts", pageCounts);
		request.setAttribute("sp", sp);
		request.setAttribute("qname", qname);
		request.getRequestDispatcher("/department/list.jsp").forward(request, response);
	}

}
