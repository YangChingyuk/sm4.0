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

import com.yqx.dao.ResumeDao;
import com.yqx.daoImpl.ResumeDaoImpl;
import com.yqx.entity.Resume;

@WebServlet("/ResumeServlet")
public class ResumeServlet extends HttpServlet {
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
		String orgname = request.getParameter("orgname");
		String job = request.getParameter("job");
		String edu = request.getParameter("edu");
		String content = request.getParameter("content");
		String result = request.getParameter("result");
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		Resume r = new Resume();
		r.setEmpId(Integer.parseInt(empId));
		r.setOrgname(orgname);
		r.setJob(job);
		r.setEdu(edu);
		r.setContent(content);
		r.setResult(result);
		try {
			r.setBeginDate(new SimpleDateFormat("yyyy-MM-dd").parse(beginDate));
			r.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ResumeDao rdao = new ResumeDaoImpl();
		rdao.add(r);
		queryByPage(request, response);
	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String empId = request.getParameter("empId");
		String orgname = request.getParameter("orgname");
		String job = request.getParameter("job");
		String edu = request.getParameter("edu");
		String content = request.getParameter("content");
		String result = request.getParameter("result");
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		Resume r = new Resume();
		r.setId(Integer.parseInt(id));
		r.setEmpId(Integer.parseInt(empId));
		r.setOrgname(orgname);
		r.setJob(job);
		r.setEdu(edu);
		r.setContent(content);
		r.setResult(result);
		try {
			r.setBeginDate(new SimpleDateFormat("yyyy-MM-dd").parse(beginDate));
			r.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		ResumeDao rdao = new ResumeDaoImpl();
		rdao.update(r);
		queryByPage(request, response);
	}

	public void deleteById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		ResumeDao rdao = new ResumeDaoImpl();
		rdao.deleteById(Integer.parseInt(id));
		queryByPage(request, response);
	}

	public void deleteMore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ids = request.getParameter("ids");
		ResumeDao rdao = new ResumeDaoImpl();
		rdao.deleteMore(ids);
		queryByPage(request, response);
	}

	public void queryById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String currentPage = request.getParameter("currentPage");
		String qorgname = request.getParameter("qorgname");
		String qempId = request.getParameter("qempId");
		
		ResumeDao rdao = new ResumeDaoImpl();
		Resume r = rdao.queryById(Integer.parseInt(id));
		request.setAttribute("resume", r);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("qorgname", qorgname);
		request.setAttribute("qempId", qempId);
		request.getRequestDispatcher("/resume/update.jsp").forward(request, response);

	}

	public void queryByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String currentPage = request.getParameter("currentPage");
		String qorgname = request.getParameter("qorgname");
		String qempId = request.getParameter("qempId");

		String condition = " where 1=1 ";
		if (qorgname != null && !qorgname.equals("") && !qorgname.equalsIgnoreCase("null")) {
			condition += " and orgname like '%" + qorgname + "%' ";
		}
		if (qempId != null && !qempId.equals("") && !qempId.equalsIgnoreCase("null")) {
			condition += " and empId =" + qempId + " ";
		}

		ResumeDao ddao = new ResumeDaoImpl();

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

		List<Resume> list = ddao.queryByPage(sp, pageSize, condition);
		request.setAttribute("list", list);
		request.setAttribute("totals", totals);
		request.setAttribute("pageCounts", pageCounts);
		request.setAttribute("sp", sp);
		request.setAttribute("qorgname", qorgname);
		request.setAttribute("qempId", qempId);
		request.getRequestDispatcher("/resume/list.jsp").forward(request, response);
	}

}
