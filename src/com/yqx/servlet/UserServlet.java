package com.yqx.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yqx.dao.EmployeeDao;
import com.yqx.dao.UserDao;
import com.yqx.daoImpl.EmployeeDaoImpl;
import com.yqx.daoImpl.UserDaoImpl;
import com.yqx.entity.Employee;
import com.yqx.entity.Student;
import com.yqx.entity.User;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
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

	public void addForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeDao edao = new EmployeeDaoImpl();
		List<Employee> elist = edao.queryAll();
		request.setAttribute("elist", elist);
		request.getRequestDispatcher("/user/add.jsp").forward(request, response);
	}

	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String empId = request.getParameter("empId");
		String state = request.getParameter("state");
		User a = new User();
		a.setUsername(username);
		a.setPassword(password);
		a.setEmpId(Integer.parseInt(empId));
		a.setState(Integer.parseInt(state));

		UserDao adao = new UserDaoImpl();
		adao.add(a);
	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String empId = request.getParameter("empId");
		String state = request.getParameter("state");
		User a = new User();
		a.setId(Integer.parseInt(id));
		a.setUsername(username);
		a.setPassword(password);
		a.setEmpId(Integer.parseInt(empId));
		a.setState(Integer.parseInt(state));
		UserDao adao = new UserDaoImpl();
		adao.update(a);
	}

	public void deleteById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		UserDao adao = new UserDaoImpl();
		adao.deleteById(Integer.parseInt(id));
		queryByPage(request, response);
	}

	public void deleteMore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ids = request.getParameter("ids");
		UserDao adao = new UserDaoImpl();
		adao.deleteMore(ids);
	}

	public void queryById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String currentPage = request.getParameter("currentPage");
		String qempId = request.getParameter("qempId");

		UserDao adao = new UserDaoImpl();
		User a = adao.queryById(Integer.parseInt(id));

		EmployeeDao edao = new EmployeeDaoImpl();
		List<Employee> elist = edao.queryAll();
		request.setAttribute("elist", elist);

		request.setAttribute("user", a);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("qempId", qempId);
		request.getRequestDispatcher("/user/update.jsp").forward(request, response);

	}

	public void queryByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取当前页码
		String page = request.getParameter("page");
		// 获取每页多少行
		String rows = request.getParameter("rows");
		String qempId = request.getParameter("qempId");
		


		String condition = " where 1=1 ";
		if (qempId != null && !qempId.equals("") && !qempId.equalsIgnoreCase("null")) {
			condition += " and empId =" + qempId + "";
		}

		UserDao adao = new UserDaoImpl();

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
		List<User> list = adao.queryByPage(sp, pageSize);
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
