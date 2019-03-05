package com.yqx.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import com.yqx.dao.StudentDao;
import com.yqx.daoImpl.StudentDaoImpl;
import com.yqx.entity.Student;

public class Test {

	public static void main(String[] args) {
		
		StudentDao sdao = new StudentDaoImpl();
//		List<Student> list = sdao.queryAll();
//		for(Student s:list) {
//			System.out.println(s);
//		}
		Random r = new Random();
		
		List<Student> list = new ArrayList<>();
		for(int i=1;i<1000;i++) {
			Student s = new Student();
			s.setName("张三"+i);
			s.setUsername("zhangsan"+i);
			s.setPassword("123456");
			s.setSex(i%2);
			int age = r.nextInt(10)+20;
			s.setAge(age);
			Calendar c = Calendar.getInstance();
			c.add(Calendar.YEAR, -age);
			s.setBirthday(c.getTime());
			s.setCreatTime(new Timestamp(System.currentTimeMillis()));
			list.add(s);
		}
		long start = System.currentTimeMillis();
		sdao.addMore(list);
		System.out.println("所需时间："+(System.currentTimeMillis()-start));
	}
}
