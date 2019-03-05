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

import com.yqx.dao.RewardsDao;
import com.yqx.daoImpl.RewardsDaoImpl;
import com.yqx.entity.Rewards;


@WebServlet("/RewardsServlet")
public class RewardsServlet extends HttpServlet {
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
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String type = request.getParameter("type");
		String creatDate = request.getParameter("creatDate");
		Rewards r = new Rewards();
		r.setEmpId(Integer.parseInt(empId));
		r.setTitle(title);
		r.setContent(content);
		r.setType(Integer.parseInt(type));
		try {
			r.setCreatDate(new SimpleDateFormat("yyyy-MM-dd").parse(creatDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		RewardsDao dao = new RewardsDaoImpl();
		dao.add(r);
		queryByPage(request, response);
	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String empId = request.getParameter("empId");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String type = request.getParameter("type");
		String creatDate = request.getParameter("creatDate");
		Rewards r = new Rewards();
		r.setId(Integer.parseInt(id));
		r.setEmpId(Integer.parseInt(empId));
		r.setTitle(title);
		r.setContent(content);
		r.setType(Integer.parseInt(type));
		try {
			r.setCreatDate(new SimpleDateFormat("yyyy-MM-dd").parse(creatDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		RewardsDao dao = new RewardsDaoImpl();
		dao.update(r);
		queryByPage(request, response);
	}

	public void deleteById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		RewardsDao dao = new RewardsDaoImpl();
		dao.deleteById(Integer.parseInt(id));
		queryByPage(request, response);
	}

	public void deleteMore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ids = request.getParameter("ids");
		RewardsDao dao = new RewardsDaoImpl();
		dao.deleteMore(ids);
		queryByPage(request, response);
	}

	public void queryById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String currentPage = request.getParameter("currentPage");
		String qempId = request.getParameter("qempId");
		RewardsDao dao = new RewardsDaoImpl();
		Rewards a = dao.queryById(Integer.parseInt(id));
		request.setAttribute("rewards", a);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("qempId", qempId);
		request.getRequestDispatcher("/rewards/update.jsp").forward(request, response);

	}

	public void queryByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String currentPage = request.getParameter("currentPage");
		String qempId = request.getParameter("qempId");

		String condition = " where 1=1 ";
		if (qempId != null && !qempId.equals("") && !qempId.equalsIgnoreCase("null")) {
			condition += " and empId ="+qempId+" ";
		}

		RewardsDao adao = new RewardsDaoImpl();

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

		List<Rewards> list = adao.queryByPage(sp, pageSize, condition);
		request.setAttribute("list", list);
		request.setAttribute("totals", totals);
		request.setAttribute("pageCounts", pageCounts);
		request.setAttribute("sp", sp);
		request.setAttribute("qempId", qempId);
		request.getRequestDispatcher("/rewards/list.jsp").forward(request, response);
	}

}
