package com.mvc.web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.web.entity.user.Login;
import com.mvc.web.entity.user.User;
import com.mvc.web.service.UserDAO;
@WebServlet("/user/login")
public class LoginController extends HttpServlet {
	private static final Integer cookieExpire = 60*60*24*30; //1month=30day
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String p_id=req.getParameter("id");
		String p_pass=req.getParameter("pass");
		String remember=req.getParameter("remember");
		
		Login lg= new Login(p_id,p_pass);  //아이디와 패스워드를 담은 객체 생성
		
	
		
		User ur=UserDAO.getInstance().LoginCheck(lg);
		int result=ur.getNumber(); //user 조회 후 결과에 대한 number
		
		switch (result) {
		case -1:
			//로그인창으로 보낸다.
			req.setAttribute("ment","PWD가 틀렸습니다");
			doGet(req, resp);
			break;
		case 0:
			req.setAttribute("ment","존재하지 않은 ID입니다");
			doGet(req, resp);
			//로그인창으로 보낸다.
			break;
		case 1:
			HttpSession session = req.getSession();
		
			//user 객체 내 정보를 변수로 이동
			String userID=ur.getId();
			String userNm=ur.getName();
			String userRank=ur.getRank();
			String userEmail=ur.getEmail();
		
			session.setAttribute("userID", userID);
			session.setAttribute("UserNm", userNm);
			session.setAttribute("userRank",userRank);
			session.setAttribute("userEmail", userEmail);
			
			if(remember!=null) {
				setCookie("sid", p_id, resp);
			}else{
				setCookie("sid", "", resp);
			}
			
			System.out.println("세션 : "+req.getSession().getAttribute("userID").toString());
			System.out.println("세션 : "+req.getSession().getAttribute("UserNm").toString());
			System.out.println("세션 : "+req.getSession().getAttribute("userRank").toString());
			System.out.println("세션 : "+req.getSession().getAttribute("userEmail").toString());
		  	
			resp.sendRedirect("/board/contents/list");
			
			//리스트로 보낸다.
			break;
		default:
			break;
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userID=get_Cookie("sid",req);
		req.setAttribute("id", userID);
		
		req.getRequestDispatcher("/WEB-INF/board/user/login.jsp").forward(req, resp);
	}
	private String get_Cookie(String cid, HttpServletRequest req) {
		String ret="";
		
		if(req==null) {
			return ret;
		}
		
		Cookie[] cookies= req.getCookies();
		if(cookies == null) {
			return ret;
		}
		for(Cookie ck : cookies) {
			if(ck.getName().equals(cid)){

				ret=ck.getValue();
				System.out.println("ck.getName : "+ck.getName());  //sid
				System.out.println("ck.getValue : "+ck.getValue()); //sid가 가진 값 : 내 아이디 값
				System.out.println("ret : "+ret);
				ck.setMaxAge(cookieExpire);
				break;
			}
		}
		return ret;
	}

	//쿠키불러오기

	//쿠키생성
	private void setCookie(String cid, String p_id, HttpServletResponse resp) {
		Cookie ck = new Cookie(cid,p_id); //sid, p_id(접속한 사람 id) //쿠키 매게변수 String String //기능은 필요하지 않을까 ?
		ck.setPath("/"); //쿠키 생성될때 경로
		ck.setMaxAge(cookieExpire); //쿠키의 보관 날짜는 
		resp.addCookie(ck); //출력부분저장
	}
}
