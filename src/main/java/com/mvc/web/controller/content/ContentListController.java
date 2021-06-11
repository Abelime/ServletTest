package com.mvc.web.controller.content;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.web.entity.content.EtcList;
import com.mvc.web.entity.content.Notice;
import com.mvc.web.service.ContentDAO;
@WebServlet("/board/contents/list")

public class ContentListController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//세션값 불러오기
		String userNm=(req.getSession().getAttribute("UserNm")).toString();
		String userRank=(req.getSession().getAttribute("userRank")).toString();
		String userID=(req.getSession().getAttribute("userID")).toString();
		
		//세션 값 테스트
		System.out.println("CONTENT-USERID : "+userID);
		System.out.println("CONTENT-userNm : "+userNm);
		System.out.println("CONTENT-userRank : "+userRank);
		
		
		
		//jsp파라미터 초기화
		int page=1;
		String page_=req.getParameter("p");
		String qurry_=req.getParameter("q");  //조회조건
		String qurry="";
		String field_=req.getParameter("f"); //검색어
		String field="title";
		
		if(page_!=null && !page_.equals("")) {
			page=Integer.parseInt(page_);
		}
		
		if(qurry_!=null && !qurry_.equals("")) {
			qurry=qurry_;
		}
		
		if(field_!=null && !field_.equals("")) {
			field=field_; 
		}
		
		//int count=ContentDAO.getInstance().getCount(field,qurry,userRank);
		
		
		EtcList el= ContentDAO.getInstance().getList(page,field, qurry,userRank);
		
		int count=el.getCount();
		List<Notice> list=el.getList();
		req.setAttribute("name", userNm);
		req.setAttribute("count", count);
		req.setAttribute("list",list); 
		
		req.getRequestDispatcher("/WEB-INF/board/content/ContentList.jsp").forward(req, resp);
	}
}
