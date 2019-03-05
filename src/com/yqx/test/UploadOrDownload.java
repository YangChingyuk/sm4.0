package com.yqx.test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

public class UploadOrDownload {
	
	//写入
	public void WriterExcel() {
		File file = new File("student.xls");
		try {
			//创建工作簿
			WritableWorkbook wwb = Workbook.createWorkbook(file);
			//创建工作表
			WritableSheet sheet = wwb.createSheet("学生信息", 0);
			//创建单元格
			Label cell1 = new Label(0, 0, "学生编号");
			Label cell2 = new Label(1, 0, "学生姓名");
			Label cell3 = new Label(2, 0, "学生帐号");
			Label cell4 = new Label(3, 0, "学生密码");
			Label cell5 = new Label(4, 0, "性别");
			Label cell6 = new Label(5, 0, "学生年龄");
			Label cell7 = new Label(6, 0, "学生出生日期");
			Label cell8 = new Label(7, 0, "创建时间");
			//把单元格添加到工作表
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
			for(int i = 1;i<=list.size();i++) {
				Student s = list.get(i-1);
				sheet.addCell(new Label(0,i,s.getId()+""));
				sheet.addCell(new Label(1,i,s.getName()+""));
				sheet.addCell(new Label(2,i,s.getUsername()+""));
				sheet.addCell(new Label(3,i,s.getPassword()+""));
				sheet.addCell(new Label(4,i,s.getSex()==1?"男":"女"));
				sheet.addCell(new Label(5,i,s.getAge()+""));
				sheet.addCell(new Label(6,i,sdf.format(s.getBirthday())+""));
				sheet.addCell(new Label(7,i,sdf.format(s.getCreatTime())+""));
			}
			
			//写入工作簿
			wwb.write();
			//关闭工作簿
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
	
	//读取
	public void ReaderExcel() {
		File file = new File("d:\\student.xls");
		try {
			//读取工作簿
			Workbook wb = Workbook.getWorkbook(file);
			//读取工作表
			Sheet sheet = wb.getSheet(0);
			//读取单元格
			//获取总行数
			int rows = sheet.getRows();
			List<Student> list = new ArrayList<>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for(int i=1;i<rows;i++) {
				Student s = new Student();
				//获取单元格内容
				String id = sheet.getCell(0,i).getContents();
				s.setId(Integer.parseInt(id));
				s.setName(sheet.getCell(1,i).getContents());
				s.setUsername(sheet.getCell(2,i).getContents());
				s.setPassword(sheet.getCell(3,i).getContents());
				s.setSex(sheet.getCell(4,i).getContents().equals("男")?1:0);
				s.setAge((Integer.parseInt(sheet.getCell(5,i).getContents())));
				s.setBirthday(sdf.parse(sheet.getCell(6,i).getContents()));
				list.add(s);
			}
			for(Student s:list) {
				System.out.println(s);
			}
			wb.close();
			
		} catch (BiffException | IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
