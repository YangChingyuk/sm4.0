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
	
	//д��
	public void WriterExcel() {
		File file = new File("student.xls");
		try {
			//����������
			WritableWorkbook wwb = Workbook.createWorkbook(file);
			//����������
			WritableSheet sheet = wwb.createSheet("ѧ����Ϣ", 0);
			//������Ԫ��
			Label cell1 = new Label(0, 0, "ѧ�����");
			Label cell2 = new Label(1, 0, "ѧ������");
			Label cell3 = new Label(2, 0, "ѧ���ʺ�");
			Label cell4 = new Label(3, 0, "ѧ������");
			Label cell5 = new Label(4, 0, "�Ա�");
			Label cell6 = new Label(5, 0, "ѧ������");
			Label cell7 = new Label(6, 0, "ѧ����������");
			Label cell8 = new Label(7, 0, "����ʱ��");
			//�ѵ�Ԫ����ӵ�������
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
				sheet.addCell(new Label(4,i,s.getSex()==1?"��":"Ů"));
				sheet.addCell(new Label(5,i,s.getAge()+""));
				sheet.addCell(new Label(6,i,sdf.format(s.getBirthday())+""));
				sheet.addCell(new Label(7,i,sdf.format(s.getCreatTime())+""));
			}
			
			//д�빤����
			wwb.write();
			//�رչ�����
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
	
	//��ȡ
	public void ReaderExcel() {
		File file = new File("d:\\student.xls");
		try {
			//��ȡ������
			Workbook wb = Workbook.getWorkbook(file);
			//��ȡ������
			Sheet sheet = wb.getSheet(0);
			//��ȡ��Ԫ��
			//��ȡ������
			int rows = sheet.getRows();
			List<Student> list = new ArrayList<>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for(int i=1;i<rows;i++) {
				Student s = new Student();
				//��ȡ��Ԫ������
				String id = sheet.getCell(0,i).getContents();
				s.setId(Integer.parseInt(id));
				s.setName(sheet.getCell(1,i).getContents());
				s.setUsername(sheet.getCell(2,i).getContents());
				s.setPassword(sheet.getCell(3,i).getContents());
				s.setSex(sheet.getCell(4,i).getContents().equals("��")?1:0);
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
