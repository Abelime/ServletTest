package com.mvc.web.controller.content;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.web.entity.content.Notice;
import com.mvc.web.service.ContentDAO;
@WebServlet("/board/contents/Regedit")
public class ContentRegeditController extends HttpServlet{
	
	
	@Override
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
	
		
		Notice nt=new Notice(userID,title,content);
		
		ContentDAO nd=new ContentDAO();
		//객체에 담아서 보내라 ?
	
		int result=ContentDAO.getInstance().regeditNotice(nt);
		
		resp.sendRedirect("list");
	
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userNm=(req.getSession().getAttribute("UserNm")).toString();
		req.setAttribute("name", userNm);
		req.getRequestDispatcher("/WEB-INF/board/content/Regedit.jsp").forward(req, resp);
	}
}
