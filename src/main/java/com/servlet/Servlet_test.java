package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class Servlet_test extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		System.out.println("Hello Servlet");
		
		//req 입력 res 출력
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset =UTF-8");
		PrintWriter out = res.getWriter();
		int cnt;
		
		if(req.getParameter("cnt")== "" || req.getParameter("cnt") ==null) {
			cnt=5;
		}else {
			 cnt=Integer.parseInt(req.getParameter("cnt"));
		}
		
		System.out.println("cnt : "+cnt);
		for(int i=0; i<=cnt; i++) {
			out.println("<h1>Hello한글 !</h1>");
		}
  	}
}
