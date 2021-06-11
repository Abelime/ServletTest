package com.mvc.web.controller.content;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.web.entity.content.Notice;
import com.mvc.web.service.ContentDAO;
@WebServlet("/board/contents/update")
public class ContentModifyController extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		String userID=(req.getSession().getAttribute("userID")).toString();

		String title_=req.getParameter("title");
		String title="";
		String content_=req.getParameter("content");
		String content="";
		System.out.println("title _ : "+title_);
		System.out.println("content_  : "+content_);
		if(title_!=null && !title_.equals("")) {
			title=title_;
		}
		if(content_!=null && !content_.equals("")) {
			content=content_;
		}
	
		int id=Integer.parseInt(req.getParameter("id"));
		
		int result=ContentDAO.getInstance().update(title,content,id);
		if(result>0) {
			resp.sendRedirect("/board/contents/Detail?id="+id);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String par = req.getParameter("id");
		String field_ =req.getParameter("f");
		String qurry_ =req.getParameter("q");
		String field="title";
		String qurry="";
		
		int id=0;
		
		if(par !=null && par.equals("")) {
			id=Integer.parseInt(par);
		}
		
		if(field_ !=null && !field_.equals("")) {
			field=field_;
		}
		
		if(qurry_ !=null && !qurry_.equals("")) {
			qurry=qurry_;
		}
		
		if(req.getSession().getAttribute("id1")!=null) {
			 id=((int) req.getSession().getAttribute("id1"));
			 req.setAttribute("msg", "수정 권한이 없습니다");
		}else {
			 id = Integer.parseInt(req.getParameter("id"));
		}
		
		Notice nt = ContentDAO.getInstance().getDetail(id);
		req.setAttribute("nt", nt);
		req.setAttribute("qurry", qurry);
		req.setAttribute("field", field);
         
		req.getRequestDispatcher("/WEB-INF/board/content/update.jsp").forward(req, resp);
	}
}
