package com.yqx.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yqx.dao.StudentDao;
import com.yqx.daoImpl.StudentDaoImpl;
import com.yqx.entity.Student;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

@WebServlet("/StudentServlet")
@MultipartConfig
public class StudentServlet extends HttpServlet {
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
		} else if (opType.equals("uploadxls")) {
			uploadxls(request, response);
		} else if (opType.equals("downloadxls")) {
			downloadxls(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void uploadxls(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 上传
		Part part = request.getPart("file");
		System.out.println("name:" + part.getName());
		System.out.println("文件类型:" + part.getContentType());
		System.out.println("文件名称:" + part.getSubmittedFileName());
		System.out.println("文件大小:" + part.getSize());
		String path = request.getServletContext().getRealPath("/upload/");
		System.out.println("文件路径:" + path);
		part.write(path + part.getSubmittedFileName());
		
		// 读取内容
		File file = new File(path + part.getSubmittedFileName());
		try { // 读取工作簿
			Workbook wb = Workbook.getWorkbook(file); // 读取工作表
			Sheet sheet = wb.getSheet(0);
			// 读取单元格
			// 获取总行数
			int rows = sheet.getRows();
			List<Student> list = new ArrayList<>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			StudentDao sdao = new StudentDaoImpl();
			for (int i = 1; i < rows; i++) {
				Student s = new Student();
				// 获取单元格内容
				String id = sheet.getCell(0, i).getContents();
				s.setId(Integer.parseInt(id));
				s.setName(sheet.getCell(1, i).getContents());
				s.setUsername(sheet.getCell(2, i).getContents());
				s.setPassword(sheet.getCell(3, i).getContents());
				s.setSex(sheet.getCell(4, i).getContents().equals("男") ? 1 : 0);
				s.setAge((Integer.parseInt(sheet.getCell(5, i).getContents())));
				s.setBirthday(sdf.parse(sheet.getCell(6, i).getContents()));
				list.add(s);
			}
			sdao.addMore(list);
			wb.close();
			request.getRequestDispatcher("/student.jsp").forward(request, response);

		} catch (BiffException | IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public String getUploadFilePath(String path, String fileName) {
		File file = new File(path + fileName);
		File d = new File(path);
		if (file.exists()) {
			String fName = fileName.substring(0, fileName.lastIndexOf("."));
			String suffix = fileName.substring(fileName.lastIndexOf("."));
			String files[] = d.list(new FilenameFilter() {

				@Override
				public boolean accept(File dir, String name) {
					name = name.substring(0, name.lastIndexOf("."));
					return name.startsWith(fName) && name.endsWith(")");
				}
			});
			fileName = fName + "(" + (files.length + 1) + ")" + suffix;
		}
		return path + fileName;
	}

	public void downloadxls(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 先把数据写入xls工作簿
		File file = new File("student.xls");
		try {
			// 创建工作簿
			WritableWorkbook wwb = Workbook.createWorkbook(file);
			// 创建工作表
			WritableSheet sheet = wwb.createSheet("学生信息", 0);
			// 创建单元格
			Label cell1 = new Label(0, 0, "学生编号");
			Label cell2 = new Label(1, 0, "学生姓名");
			Label cell3 = new Label(2, 0, "学生帐号");
			Label cell4 = new Label(3, 0, "学生密码");
			Label cell5 = new Label(4, 0, "性别");
			Label cell6 = new Label(5, 0, "学生年龄");
			Label cell7 = new Label(6, 0, "学生出生日期");
			Label cell8 = new Label(7, 0, "创建时间");
			// 把单元格添加到工作表
			sheet.addCell(cell1);
			sheet.addCell(cell2);
			sheet.addCell(cell3);
			sheet.addCell(cell4);
			sheet.addCell(cell5);
			sheet.addCell(cell6);
			sheet.addCell(cell7);
			sheet.addCell(cell8);

			StudentDao sdao = new StudentDaoImpl();
			List<Student> list = sdao.queryAll();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for (int i = 1; i <= list.size(); i++) {
				Student s = list.get(i - 1);
				sheet.addCell(new Label(0, i, s.getId() + ""));
				sheet.addCell(new Label(1, i, s.getName() + ""));
				sheet.addCell(new Label(2, i, s.getUsername() + ""));
				sheet.addCell(new Label(3, i, s.getPassword() + ""));
				sheet.addCell(new Label(4, i, s.getSex() == 1 ? "男" : "女"));
				sheet.addCell(new Label(5, i, s.getAge() + ""));
				sheet.addCell(new Label(6, i, sdf.format(s.getBirthday()) + ""));
				sheet.addCell(new Label(7, i, sdf.format(s.getCreatTime()) + ""));
			}

			// 写入工作簿
			wwb.write();
			// 关闭工作簿
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}

		// 下载
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// 获取下载的文件名称
		String fileName = request.getParameter("fileName");
		// 获取下载文件存放的路径
		// String dpath = request.getServletContext().getRealPath("/upload/");
		// 下载文件对象
		// File file = new File(dpath,fileName);
		// 打开文件输入流
		FileInputStream fis = new FileInputStream(file);
		// 打开文件输出流
		OutputStream os = response.getOutputStream();
		// 对中文字符url编码
		fileName = URLEncoder.encode(fileName, "utf-8");
		// 设置文件下载头部信息
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName + "");
		// 下载文件
		byte[] b = new byte[2048];
		int len = 0;
		while ((len = fis.read(b)) != -1) {
			os.write(b, 0, len);
		}
		fis.close();
		os.close();
	}

	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		JSONObject jo = new JSONObject();
		try {
			String name = request.getParameter("name");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String sex = request.getParameter("sex");
			String age = request.getParameter("age");
			String birthday = request.getParameter("birthday");
			Student s = new Student();
			s.setName(name);
			s.setUsername(username);
			s.setPassword(password);
			s.setSex(Integer.parseInt(sex));
			s.setAge(Integer.parseInt(age));
			try {
				s.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			StudentDao dao = new StudentDaoImpl();
			dao.add(s);
			jo.put("state", 0);
			jo.put("msg", "成功新增记录");
		} catch (Exception e) {
			jo.put("state", -1);
			jo.put("msg", "新增记录失败" + e.getMessage());
		} finally {
			String str = JSON.toJSONString(jo);
			System.out.println(str);
			out.write(str);
			out.flush();
			out.close();
		}

	}

	public void deleteById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		StudentDao dao = new StudentDaoImpl();
		dao.deleteById(Integer.parseInt(id));
		queryByPage(request, response);
	}

	public void deleteMore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		JSONObject jo = new JSONObject();
		try {
			String ids = request.getParameter("ids");
			StudentDao dao = new StudentDaoImpl();
			dao.deleteMore(ids);
			jo.put("state", 0);
			jo.put("msg", "成功删除记录");
		} catch (Exception e) {
			jo.put("state", -1);
			jo.put("msg", "删除记录失败" + e.getMessage());
		} finally {
			String str = JSON.toJSONString(jo);
			System.out.println(str);
			out.write(str);
			out.flush();
			out.close();
		}

	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		JSONObject jo = new JSONObject();
		try {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String sex = request.getParameter("sex");
			String age = request.getParameter("age");
			String birthday = request.getParameter("birthday");
			Student s = new Student();
			s.setId(Integer.parseInt(id));
			s.setName(name);
			s.setUsername(username);
			s.setPassword(password);
			s.setSex(Integer.parseInt(sex));
			s.setAge(Integer.parseInt(age));
			try {
				s.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			StudentDao dao = new StudentDaoImpl();
			dao.update(s);
			jo.put("state", 0);
			jo.put("msg", "成功修改记录");
		} catch (Exception e) {
			jo.put("state", -1);
			jo.put("msg", "修改记录失败" + e.getMessage());
		} finally {
			String str = JSON.toJSONString(jo);
			System.out.println(str);
			out.write(str);
			out.flush();
			out.close();
		}
	}

	public void queryById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String currentPage = request.getParameter("currentPage");
		String qname = request.getParameter("qname");
		String qusername = request.getParameter("qusername");
		String qsex = request.getParameter("qsex");
		StudentDao dao = new StudentDaoImpl();
		Student s = dao.queryById(Integer.parseInt(id));
		request.setAttribute("student", s);
		request.setAttribute("qname", qname);
		request.setAttribute("qusername", qusername);
		request.setAttribute("qsex", qsex);
		request.setAttribute("currentPage", currentPage);
		request.getRequestDispatcher("/update.jsp").forward(request, response);
	}

	public void queryAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void queryByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String qname = request.getParameter("qname");
		String qusername = request.getParameter("qusername");
		String qsex = request.getParameter("qsex");
		String qbeginDate = request.getParameter("qbeginDate");
		String qendDate = request.getParameter("qendDate");

		String currentPage = request.getParameter("page");
		String rows = request.getParameter("rows");

		String condition = " where 1=1 ";
		if (qname != null && !qname.equals("") && !qname.equalsIgnoreCase("null")) {
			condition += " and name like '%" + qname + "%'";
		}
		if (qusername != null && !qusername.equals("") && !qname.equalsIgnoreCase("null")) {
			condition += " and username like '%" + qusername + "%'";
		}
		if (qsex != null && !qsex.equals("") && !qsex.equals("-1") && !qname.equalsIgnoreCase("null")) {
			condition += " and sex = " + qsex;
		}
		if (qbeginDate != null && !qbeginDate.equals("")) {
			condition += " and birthday >= '" + qbeginDate + "'";
		}
		if (qendDate != null && !qendDate.equals("")) {
			condition += " and birthday <= '" + qendDate + "'";
		}
		StudentDao dao = new StudentDaoImpl();

		int sp = 1;

		int totals = dao.getTotals(condition);

		int pageSize = Integer.parseInt(rows);

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
		List<Student> list = dao.queryByPage(sp, pageSize, condition);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		JSONObject jo = new JSONObject();
		jo.put("total", totals);
		jo.put("rows", list);
		String json = JSON.toJSONString(jo);
		System.out.println(json);
		out.write(json);
		out.flush();
		out.close();
	}
}
