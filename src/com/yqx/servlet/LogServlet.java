package com.yqx.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yqx.dao.LogDao;
import com.yqx.daoImpl.LogDaoImpl;
import com.yqx.entity.Log;
import com.yqx.entity.User;


@WebServlet("/LogServlet")
public class LogServlet extends HttpServlet {
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
		String uid = request.getParameter("uid");
		String loginTime = request.getParameter("loginTime");
		String logoutTime = request.getParameter("logoutTime");
		Log a = new Log();
		a.setUid(Integer.parseInt(uid));
		try {
			a.setLoginTime(new SimpleDateFormat("yyyy-MM-dd").parse(loginTime));
			a.setLogoutTime(new SimpleDateFormat("yyyy-MM-dd").parse(logoutTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		LogDao adao = new LogDaoImpl();
		adao.add(a);
	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String uid = request.getParameter("uid");
		String loginTime = request.getParameter("loginTime");
		String logoutTime = request.getParameter("logoutTime");
		Log a = new Log();
		a.setId(Integer.parseInt(id));
		a.setUid(Integer.parseInt(uid));
		try {
			a.setLoginTime(new SimpleDateFormat("yyyy-MM-dd").parse(loginTime));
			a.setLogoutTime(new SimpleDateFormat("yyyy-MM-dd").parse(logoutTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		LogDao adao = new LogDaoImpl();
		adao.update(a);
	}

	public void deleteById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		LogDao adao = new LogDaoImpl();
		adao.deleteById(Integer.parseInt(id));
		queryByPage(request, response);
	}

	public void deleteMore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ids = request.getParameter("ids");
		LogDao adao = new LogDaoImpl();
		adao.deleteMore(ids);
	}

	public void queryById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String currentPage = request.getParameter("currentPage");
		String quid = request.getParameter("quid");
		LogDao adao = new LogDaoImpl();
		Log a = adao.queryById(Integer.parseInt(id));
		request.setAttribute("log", a);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("quid", quid);
		request.getRequestDispatcher("/log/update.jsp").forward(request, response);

	}

	public void queryByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取当前页码
		String page = request.getParameter("page");
		// 获取每页多少行
		String rows = request.getParameter("rows");
		String quid = request.getParameter("quid");

		String condition = " where 1=1 ";
		if (quid != null && !quid.equals("") && !quid.equalsIgnoreCase("null")) {
			condition += " and uid =" + quid + "";
		}

		LogDao adao = new LogDaoImpl();

		// 当前页数
		int sp = 1;
		// 每页显示的记录数
		int pageSize = Integer.parseInt(rows);
		// 总记录数
		int totals = adao.getTotals(condition);
		// 总页数
		int pageCounts = totals / pageSize;
		if (totals % pageSize != 0) {
			pageCounts++;
		}
		try {
			sp = Integer.parseInt(page);
		} catch (Exception e) {
			sp = 1;
		}

		if (sp > pageCounts) {
			sp = pageCounts;
		}
		if (sp < 1) {
			sp = 1;
		}

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		List<Log> list = adao.queryByPage(sp, pageSize);
		//String jsonStr = JSONArray.toJSONString(list);
		//{"total":28,"rows":[]}
		//out.write("{\"total\":"+totals+",\"rows\":"+jsonStr+"}");
		
		JSONObject jo = new JSONObject();
		jo.put("total", totals);
		jo.put("rows", list);
		String json = JSONArray.toJSONString(jo);
		out.write(json);
		
		out.flush();
		out.close();
	}

}
