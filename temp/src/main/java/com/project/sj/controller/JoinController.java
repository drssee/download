package com.project.sj.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.sj.SingletonService;
import com.project.sj.domain.User;
import com.project.sj.service.UserService;

@WebServlet(name="joinController" , value="/join")
public class JoinController extends UserController {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("register ... get");
		req.getRequestDispatcher("/WEB-INF/join.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("register ... post");

		HttpSession session = req.getSession();
		
		//폼태그 입력값 유저객체에 저장
		String phone = req.getParameter("phone1") 
				+ req.getParameter("phone2") 
				+ req.getParameter("phone3");
		Integer age=Integer.parseInt(req.getParameter("age"));
		Integer gender=Integer.parseInt(req.getParameter("gender"));
		User user = User.builder()
				.id(req.getParameter("id"))
				.pwd(req.getParameter("pwd"))
				.name(req.getParameter("name"))
				.email(req.getParameter("email"))
				.phone(phone)
				.age(age)
				.gender(gender)
			.build();
		System.out.println("User : "+user);
		//회원가입후 세션에 저장
		userService.join(user);
		//회원 로그인 기억 세션
		session.setAttribute("user", user.getId());
		resp.sendRedirect("/");
	}
}
