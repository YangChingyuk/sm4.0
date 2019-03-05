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

import com.yqx.dao.DeployDao;
import com.yqx.daoImpl.DeployDaoImpl;
import com.yqx.entity.Deploy;


@WebServlet("/DeployServlet")
public class DeployServlet extends HttpServlet {
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
		String olddept = request.getParameter("olddept");
		String newdept = request.getParameter("newdept");
		String oldjob = request.getParameter("oldjob");
		String newjob = request.getParameter("newjob");
		String mixDate = request.getParameter("mixDate");
		Deploy r = new Deploy();
		r.setEmpId(Integer.parseInt(empId));
		r.setOlddept(olddept);
		r.setNewdept(newdept);
		r.setOldjob(oldjob);
		r.setNewjob(newjob);
		try {
			r.setMixDate(new SimpleDateFormat("yyyy-MM-dd").parse(mixDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		DeployDao dao = new DeployDaoImpl();
		dao.add(r);
		queryByPage(request, response);
	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String empId = request.getParameter("empId");
		String olddept = request.getParameter("olddept");
		String newdept = request.getParameter("newdept");
		String oldjob = request.getParameter("oldjob");
		String newjob = request.getParameter("newjob");
		String mixDate = request.getParameter("mixDate");
		Deploy r = new Deploy();
		r.setId(Integer.parseInt(id));
		r.setEmpId(Integer.parseInt(empId));
		r.setOlddept(olddept);
		r.setNewdept(newdept);
		r.setOldjob(oldjob);
		r.setNewjob(newjob);
		try {
			r.setMixDate(new SimpleDateFormat("yyyy-MM-dd").parse(mixDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		DeployDao dao = new DeployDaoImpl();
		dao.update(r);
		queryByPage(request, response);
	}

	public void deleteById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		DeployDao dao = new DeployDaoImpl();
		dao.deleteById(Integer.parseInt(id));
		queryByPage(request, response);
	}

	public void deleteMore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ids = request.getParameter("ids");
		DeployDao dao = new DeployDaoImpl();
		dao.deleteMore(ids);
		queryByPage(request, response);
	}

	public void queryById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String currentPage = request.getParameter("currentPage");
		String qempId = request.getParameter("qempId");
		DeployDao dao = new DeployDaoImpl();
		Deploy a = dao.queryById(Integer.parseInt(id));
		request.setAttribute("deploy", a);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("qempId", qempId);
		request.getRequestDispatcher("/deploy/update.jsp").forward(request, response);

	}

	public void queryByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String currentPage = request.getParameter("currentPage");
		String qempId = request.getParameter("qempId");

		String condition = " where 1=1 ";
		if (qempId != null && !qempId.equals("") && !qempId.equalsIgnoreCase("null")) {
			condition += " and empId ="+qempId+" ";
		}

		DeployDao adao = new DeployDaoImpl();

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

		List<Deploy> list = adao.queryByPage(sp, pageSize, condition);
		request.setAttribute("list", list);
		request.setAttribute("totals", totals);
		request.setAttribute("pageCounts", pageCounts);
		request.setAttribute("sp", sp);
		request.setAttribute("qempId", qempId);
		request.getRequestDispatcher("/deploy/list.jsp").forward(request, response);
	}

}
