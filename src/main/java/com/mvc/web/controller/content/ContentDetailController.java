package com.mvc.web.controller.content;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.web.entity.content.Notice;
import com.mvc.web.service.ContentDAO;

@WebServlet("/board/contents/Detail")
public class ContentDetailController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		resp.sendRedirect("/list");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ContentDAO nd = new ContentDAO();
		String par = req.getParameter("id");  //정수라고<<
		
	
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
		ContentDAO.getInstance().UpHit(id);
		
		
		Notice nt = ContentDAO.getInstance().getDetail(id);
		req.setAttribute("nt", nt);
		req.setAttribute("qurry", qurry);
		req.setAttribute("field", field);
		
		req.getRequestDispatcher("/WEB-INF/board/content/Detail.jsp").forward(req, resp);
	}
}
