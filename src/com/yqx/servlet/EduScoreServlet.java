package com.yqx.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yqx.dao.EduDao;
import com.yqx.dao.EduScoreDao;
import com.yqx.daoImpl.EduDaoImpl;
import com.yqx.daoImpl.EduScoreDaoImpl;
import com.yqx.entity.Edu;
import com.yqx.entity.EduScore;

@WebServlet("/EduScoreServlet")
public class EduScoreServlet extends HttpServlet {
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
		String eduid = request.getParameter("eduid");
		String score = request.getParameter("score");
		EduScore a = new EduScore();
		a.setEmpId(Integer.parseInt(empId));
		a.setEduid(Integer.parseInt(eduid));
		a.setScore(Integer.parseInt(score));

		EduScoreDao adao = new EduScoreDaoImpl();
		adao.add(a);
		queryByPage(request, response);
	}
	
	public void addForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EduDao edao = new EduDaoImpl();
		List<Edu> elist = edao.queryAll();
		request.setAttribute("elist", elist);
		request.getRequestDispatcher("/edu/add.jsp").forward(request, response);
	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String empId = request.getParameter("empId");
		String eduid = request.getParameter("eduid");
		String score = request.getParameter("score");
		EduScore a = new EduScore();
		a.setId(Integer.parseInt(id));
		a.setEmpId(Integer.parseInt(empId));
		a.setEduid(Integer.parseInt(eduid));
		a.setScore(Integer.parseInt(score));
		EduScoreDao adao = new EduScoreDaoImpl();
		adao.update(a);
		queryByPage(request, response);
	}

	public void deleteById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		EduScoreDao adao = new EduScoreDaoImpl();
		adao.deleteById(Integer.parseInt(id));
		queryByPage(request, response);
	}

	public void deleteMore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ids = request.getParameter("ids");
		EduScoreDao adao = new EduScoreDaoImpl();
		adao.deleteMore(ids);
		queryByPage(request, response);
	}

	public void queryById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String currentPage = request.getParameter("currentPage");
		String qempId = request.getParameter("qempId");
		EduScoreDao adao = new EduScoreDaoImpl();
		EduScore a = adao.queryById(Integer.parseInt(id));
		
		EduDao edao = new EduDaoImpl();
		List<Edu> elist = edao.queryAll();
		request.setAttribute("elist", elist);
		
		request.setAttribute("eduScore", a);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("qempId", qempId);
		request.getRequestDispatcher("/eduScore/update.jsp").forward(request, response);

	}

	public void queryByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String currentPage = request.getParameter("currentPage");
		String qempId = request.getParameter("qempId");

		String condition = " where 1=1 ";
		if (qempId != null && !qempId.equals("") && !qempId.equalsIgnoreCase("null")) {
			condition += " and empId =" + qempId + " ";
		}

		EduScoreDao adao = new EduScoreDaoImpl();

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

		List<EduScore> list = adao.queryByPage(sp, pageSize, condition);
		request.setAttribute("list", list);
		request.setAttribute("totals", totals);
		request.setAttribute("pageCounts", pageCounts);
		request.setAttribute("sp", sp);
		request.setAttribute("qempId", qempId);
		request.getRequestDispatcher("/eduScore/list.jsp").forward(request, response);
	}

}
