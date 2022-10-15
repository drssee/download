package com.project.sj.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.sj.SingletonService;
import com.project.sj.service.UserService;
import lombok.extern.slf4j.Slf4j;

public class UserController extends HttpServlet{
	//**session id 목록**
	// user - 로그인여부 저장 (userid,adminid)
	// islogin - 아이디저장과 로그인실패 저장 (true,false,fail)
	SingletonService instance = SingletonService.getInstance();
	UserService userService = instance.userService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
}
