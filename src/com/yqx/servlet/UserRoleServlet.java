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
import com.yqx.dao.UserRoleDao;
import com.yqx.daoImpl.UserRoleDaoImpl;
import com.yqx.entity.Menu;
import com.yqx.entity.UserRole;

@WebServlet("/UserRoleServlet")
public class UserRoleServlet extends HttpServlet {
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
		String rid = request.getParameter("rid");
		UserRole a = new UserRole();
		a.setUid(Integer.parseInt(uid));
		a.setRid(Integer.parseInt(rid));
		UserRoleDao adao = new UserRoleDaoImpl();
		adao.add(a);
	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String uid = request.getParameter("uid");
		String rid = request.getParameter("rid");
		UserRole a = new UserRole();
		a.setId(Integer.parseInt(id));
		a.setUid(Integer.parseInt(uid));
		a.setRid(Integer.parseInt(rid));
		UserRoleDao adao = new UserRoleDaoImpl();
		adao.update(a);
	}

	public void deleteById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		UserRoleDao adao = new UserRoleDaoImpl();
		adao.deleteById(Integer.parseInt(id));
		queryByPage(request, response);
	}

	public void deleteMore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ids = request.getParameter("ids");
		UserRoleDao adao = new UserRoleDaoImpl();
		adao.deleteMore(ids);
	}

	public void queryById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String currentPage = request.getParameter("currentPage");
		String quid = request.getParameter("quid");
		String qrid = request.getParameter("qrid");
		UserRoleDao adao = new UserRoleDaoImpl();
		UserRole a = adao.queryById(Integer.parseInt(id));
		request.setAttribute("userRole", a);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("quid", quid);
		request.setAttribute("qrid", qrid);
		request.getRequestDispatcher("/userRole/update.jsp").forward(request, response);

	}

	public void queryByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取当前页码
		String page = request.getParameter("page");
		// 获取每页多少行
		String rows = request.getParameter("rows");
		
		String quid = request.getParameter("quid");
		String qrid = request.getParameter("qrid");

		String condition = " where 1=1 ";
		if (quid != null && !quid.equals("") && !quid.equalsIgnoreCase("null")) {
			condition += " and uid =" + quid + " ";
		}
		if (qrid != null && !qrid.equals("") && !qrid.equalsIgnoreCase("null")) {
			condition += " and rid =" + qrid + " ";
		}

		UserRoleDao adao = new UserRoleDaoImpl();

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
		List<UserRole> list = adao.queryByPage(sp, pageSize);
		// String jsonStr = JSONArray.toJSONString(list);
		// {"total":28,"rows":[]}
		// out.write("{\"total\":"+totals+",\"rows\":"+jsonStr+"}");

		JSONObject jo = new JSONObject();
		jo.put("total", totals);
		jo.put("rows", list);
		String json = JSONArray.toJSONString(jo);
		out.write(json);

		out.flush();
		out.close();
	}

}
