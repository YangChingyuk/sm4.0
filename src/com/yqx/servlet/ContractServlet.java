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

import com.yqx.dao.ContractDao;
import com.yqx.daoImpl.ContractDaoImpl;
import com.yqx.entity.Contract;

@WebServlet("/ContractServlet")
public class ContractServlet extends HttpServlet {
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
		String code = request.getParameter("code");
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String job = request.getParameter("job");
		String content = request.getParameter("content");
		String attachment = request.getParameter("attachment");
		Contract c = new Contract();
		c.setEmpId(Integer.parseInt(empId));
		c.setCode(code);
		try {
			c.setBeginDate(new SimpleDateFormat("yyyy-MM-dd").parse(beginDate));
			c.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setJob(job);
		c.setContent(content);
		c.setAttachment(attachment);

		ContractDao dao = new ContractDaoImpl();
		dao.add(c);
		queryByPage(request, response);
	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String empId = request.getParameter("empId");
		String code = request.getParameter("code");
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String job = request.getParameter("job");
		String content = request.getParameter("content");
		String attachment = request.getParameter("attachment");
		Contract c = new Contract();
		c.setId(Integer.parseInt(id));
		c.setEmpId(Integer.parseInt(empId));
		c.setCode(code);
		try {
			c.setBeginDate(new SimpleDateFormat("yyyy-MM-dd").parse(beginDate));
			c.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setJob(job);
		c.setContent(content);
		c.setAttachment(attachment);
		ContractDao dao = new ContractDaoImpl();
		dao.update(c);
		queryByPage(request, response);
	}

	public void deleteById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		ContractDao adao = new ContractDaoImpl();
		adao.deleteById(Integer.parseInt(id));
		queryByPage(request, response);
	}

	public void deleteMore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ids = request.getParameter("ids");
		ContractDao adao = new ContractDaoImpl();
		adao.deleteMore(ids);
		queryByPage(request, response);
	}

	public void queryById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String currentPage = request.getParameter("currentPage");
		String qempId = request.getParameter("qempId");
		
		ContractDao dao = new ContractDaoImpl();
		Contract c = dao.queryById(Integer.parseInt(id));
		request.setAttribute("contract", c);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("qempId", qempId);
		request.getRequestDispatcher("/contract/update.jsp").forward(request, response);

	}

	public void queryByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String currentPage = request.getParameter("currentPage");
		String qempId = request.getParameter("qempId");

		String condition = " where 1=1 ";
		if (qempId != null && !qempId.equals("") && !qempId.equalsIgnoreCase("null")) {
			condition += " and empId =" + qempId;
		}

		ContractDao adao = new ContractDaoImpl();

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

		List<Contract> list = adao.queryByPage(sp, pageSize, condition);
		request.setAttribute("list", list);
		request.setAttribute("totals", totals);
		request.setAttribute("pageCounts", pageCounts);
		request.setAttribute("sp", sp);
		request.setAttribute("qempId", qempId);
		request.getRequestDispatcher("/contract/list.jsp").forward(request, response);
	}

}
