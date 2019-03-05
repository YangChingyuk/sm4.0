package com.yqx.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yqx.dao.EduDao;
import com.yqx.dao.EduTypeDao;
import com.yqx.daoImpl.EduDaoImpl;
import com.yqx.daoImpl.EduTypeDaoImpl;
import com.yqx.entity.Edu;
import com.yqx.entity.EduType;

@WebServlet("/EduServlet")
public class EduServlet extends HttpServlet {
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
		String name = request.getParameter("name");
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String address = request.getParameter("address");
		String type = request.getParameter("type");
		Edu a = new Edu();
		a.setName(name);
		try {
			a.setBeginDate(new SimpleDateFormat("yyyy-MM-dd").parse(beginDate));
			a.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		a.setAddress(address);
		a.setType(Integer.parseInt(type));

		EduDao adao = new EduDaoImpl();
		adao.add(a);
		queryByPage(request, response);
	}

	public void addForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EduTypeDao etdao = new EduTypeDaoImpl();
		List<EduType> elist = etdao.queryAll();
		request.setAttribute("elist", elist);
		request.getRequestDispatcher("/edu/add.jsp").forward(request, response);
	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String address = request.getParameter("address");
		String type = request.getParameter("type");
		Edu a = new Edu();
		a.setId(Integer.parseInt(id));
		a.setName(name);
		try {
			a.setBeginDate(new SimpleDateFormat("yyyy-MM-dd").parse(beginDate));
			a.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		a.setAddress(address);
		a.setType(Integer.parseInt(type));
		EduDao adao = new EduDaoImpl();
		adao.update(a);
		queryByPage(request, response);
	}

	public void deleteById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		EduDao adao = new EduDaoImpl();
		adao.deleteById(Integer.parseInt(id));
		queryByPage(request, response);
	}

	public void deleteMore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ids = request.getParameter("ids");
		EduDao adao = new EduDaoImpl();
		adao.deleteMore(ids);
		queryByPage(request, response);
	}

	public void queryById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String currentPage = request.getParameter("currentPage");
		String qname = request.getParameter("qname");

		EduDao adao = new EduDaoImpl();
		Edu a = adao.queryById(Integer.parseInt(id));

		EduTypeDao etdao = new EduTypeDaoImpl();
		List<EduType> elist = etdao.queryAll();

		request.setAttribute("edu", a);
		request.setAttribute("elist", elist);

		request.setAttribute("currentPage", currentPage);
		request.setAttribute("qname", qname);
		request.getRequestDispatcher("/edu/update.jsp").forward(request, response);

	}

	public void queryByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String currentPage = request.getParameter("currentPage");
		String qname = request.getParameter("qname");

		String condition = " where 1=1 ";
		if (qname != null && !qname.equals("") && !qname.equalsIgnoreCase("null")) {
			condition += " and name like '%" + qname + "%' ";
		}

		EduDao adao = new EduDaoImpl();

		// 当前页数
		int sp = 1;
		// 每页显示的记录数
		int pageSize = 20;
		// 总记录数
		int totals = adao.getTotals(condition);
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

		List<Edu> list = adao.queryByPage(sp, pageSize, condition);
		request.setAttribute("list", list);
		request.setAttribute("totals", totals);
		request.setAttribute("pageCounts", pageCounts);
		request.setAttribute("sp", sp);
		request.setAttribute("qname", qname);
		request.getRequestDispatcher("/edu/list.jsp").forward(request, response);
	}

}
