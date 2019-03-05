package com.yqx.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yqx.dao.ArchivesDao;
import com.yqx.dao.ArchivesTypeDao;
import com.yqx.daoImpl.ArchivesDaoImpl;
import com.yqx.daoImpl.ArchivesTypeDaoImpl;
import com.yqx.entity.Archives;
import com.yqx.entity.ArchivesType;

@WebServlet("/ArchivesServlet")
public class ArchivesServlet extends HttpServlet {
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

	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empId = request.getParameter("empId");
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		String type = request.getParameter("type");
		String remark = request.getParameter("remark");
		Archives a = new Archives();
		a.setEmpId(Integer.parseInt(empId));
		a.setCode(code);
		a.setName(name);
		a.setContent(content);
		a.setType(Integer.parseInt(type));
		a.setRemark(remark);
		ArchivesDao adao = new ArchivesDaoImpl();
		adao.add(a);
		queryByPage(request, response);
	}
	
	public void addForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArchivesTypeDao tdao = new ArchivesTypeDaoImpl();
		List<ArchivesType> alist = tdao.queryAll();
		request.setAttribute("alist", alist);
		request.getRequestDispatcher("/archives/add.jsp").forward(request, response);
	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String empId = request.getParameter("empId");
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		String type = request.getParameter("type");
		String remark = request.getParameter("remark");
		Archives a = new Archives();
		a.setId(Integer.parseInt(id));
		a.setEmpId(Integer.parseInt(empId));
		a.setCode(code);
		a.setName(name);
		a.setContent(content);
		a.setType(Integer.parseInt(type));
		a.setRemark(remark);
		ArchivesDao adao = new ArchivesDaoImpl();
		adao.update(a);
		queryByPage(request, response);
	}

	public void deleteById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		ArchivesDao adao = new ArchivesDaoImpl();
		adao.deleteById(Integer.parseInt(id));
		queryByPage(request, response);
	}

	public void deleteMore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ids = request.getParameter("ids");
		ArchivesDao adao = new ArchivesDaoImpl();
		adao.deleteMore(ids);
		queryByPage(request, response);
	}

	public void queryById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String currentPage = request.getParameter("currentPage");
		String qname = request.getParameter("qname");
		String qtype = request.getParameter("qtype");

		ArchivesDao adao = new ArchivesDaoImpl();
		Archives a = adao.queryById(Integer.parseInt(id));

		ArchivesTypeDao tdao = new ArchivesTypeDaoImpl();
		List<ArchivesType> alist = tdao.queryAll();

		request.setAttribute("archives", a);
		request.setAttribute("alist", alist);

		request.setAttribute("currentPage", currentPage);
		request.setAttribute("qname", qname);
		request.setAttribute("qtype", qtype);
		request.getRequestDispatcher("/archives/update.jsp").forward(request, response);

	}

	public void queryByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String currentPage = request.getParameter("currentPage");
		String qname = request.getParameter("qname");
		String qtype = request.getParameter("qtype");

		String condition = " where 1=1 ";
		if (qname != null && !qname.equals("") && !qname.equalsIgnoreCase("null")) {
			condition += " and name like '%" + qname + "%' ";
		}
		if (qtype != null && !qtype.equals("") && !qtype.equalsIgnoreCase("null")) {
			condition += " and type =" + qtype + " ";
		}

		ArchivesDao ddao = new ArchivesDaoImpl();

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

		List<Archives> list = ddao.queryByPage(sp, pageSize, condition);
		request.setAttribute("list", list);
		request.setAttribute("totals", totals);
		request.setAttribute("pageCounts", pageCounts);
		request.setAttribute("sp", sp);
		request.setAttribute("qname", qname);
		request.setAttribute("qtype", qtype);
		request.getRequestDispatcher("/archives/list.jsp").forward(request, response);
	}

}
