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
import com.yqx.dao.MenuDao;
import com.yqx.daoImpl.MenuDaoImpl;
import com.yqx.entity.Menu;
import com.yqx.entity.Role;

@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
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
		String code = request.getParameter("code");
		String pcode = request.getParameter("pcode");
		String name = request.getParameter("name");
		String url = request.getParameter("url");
		String state = request.getParameter("state");
		String remark = request.getParameter("remark");
		Menu a = new Menu();
		a.setCode(code);
		a.setPcode(pcode);
		a.setName(name);
		a.setUrl(url);
		a.setState(Integer.parseInt(state));
		a.setRemark(remark);
		MenuDao adao = new MenuDaoImpl();
		adao.add(a);
	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String code = request.getParameter("code");
		String pcode = request.getParameter("pcode");
		String name = request.getParameter("name");
		String url = request.getParameter("url");
		String state = request.getParameter("state");
		String remark = request.getParameter("remark");
		Menu a = new Menu();
		a.setId(Integer.parseInt(id));
		a.setCode(code);
		a.setPcode(pcode);
		a.setName(name);
		a.setUrl(url);
		a.setState(Integer.parseInt(state));
		a.setRemark(remark);
		MenuDao adao = new MenuDaoImpl();
		adao.update(a);
	}

	public void deleteById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		MenuDao adao = new MenuDaoImpl();
		adao.deleteById(Integer.parseInt(id));
		queryByPage(request, response);
	}

	public void deleteMore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ids = request.getParameter("ids");
		MenuDao adao = new MenuDaoImpl();
		adao.deleteMore(ids);
	}

	public void queryById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String currentPage = request.getParameter("currentPage");
		String qname = request.getParameter("qname");
		MenuDao adao = new MenuDaoImpl();
		Menu a = adao.queryById(Integer.parseInt(id));
		request.setAttribute("menu", a);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("qname", qname);
		request.getRequestDispatcher("/menu/update.jsp").forward(request, response);

	}

	public void queryByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取当前页码
		String page = request.getParameter("page");
		// 获取每页多少行
		String rows = request.getParameter("rows");
		String qname = request.getParameter("qname");

		String condition = " where 1=1 ";
		if (qname != null && !qname.equals("") && !qname.equalsIgnoreCase("null")) {
			condition += " and name like '%" + qname + "%' ";
		}

		MenuDao adao = new MenuDaoImpl();

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
		List<Menu> list = adao.queryByPage(sp, pageSize);
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
