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

import com.yqx.dao.EngageDao;
import com.yqx.daoImpl.EngageDaoImpl;
import com.yqx.entity.Engage;

@WebServlet("/EngageServlet")
public class EngageServlet extends HttpServlet {
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
		String empId = request.getParameter("empId");
		String skillname = request.getParameter("skillname");
		String createDate = request.getParameter("createDate");
		Engage a = new Engage();
		a.setEmpId(Integer.parseInt(empId));
		a.setSkillname(skillname);
		try {
			a.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").parse(createDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		EngageDao dao = new EngageDaoImpl();
		dao.add(a);
		queryByPage(request, response);
	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String empId = request.getParameter("empId");
		String skillname = request.getParameter("skillname");
		String createDate = request.getParameter("createDate");
		Engage a = new Engage();
		a.setId(Integer.parseInt(id));
		a.setEmpId(Integer.parseInt(empId));
		a.setSkillname(skillname);
		try {
			a.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").parse(createDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		EngageDao dao = new EngageDaoImpl();
		dao.update(a);
		queryByPage(request, response);
	}

	public void deleteById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		EngageDao dao = new EngageDaoImpl();
		dao.deleteById(Integer.parseInt(id));
		queryByPage(request, response);
	}

	public void deleteMore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ids = request.getParameter("ids");
		EngageDao dao = new EngageDaoImpl();
		dao.deleteMore(ids);
		queryByPage(request, response);
	}

	public void queryById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String currentPage = request.getParameter("currentPage");
		String qempId = request.getParameter("qempId");
		EngageDao adao = new EngageDaoImpl();
		Engage a = adao.queryById(Integer.parseInt(id));
		request.setAttribute("engage", a);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("qempId", qempId);
		request.getRequestDispatcher("/engage/update.jsp").forward(request, response);

	}

	public void queryByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String currentPage = request.getParameter("currentPage");
		String qempId = request.getParameter("qempId");

		String condition = " where 1=1 ";
		if (qempId != null && !qempId.equals("") && !qempId.equalsIgnoreCase("null")) {
			condition += " and empId =" + qempId + " ";
		}

		EngageDao adao = new EngageDaoImpl();

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

		List<Engage> list = adao.queryByPage(sp, pageSize, condition);
		request.setAttribute("list", list);
		request.setAttribute("totals", totals);
		request.setAttribute("pageCounts", pageCounts);
		request.setAttribute("sp", sp);
		request.setAttribute("qempId", qempId);
		request.getRequestDispatcher("/engage/list.jsp").forward(request, response);
	}

}
