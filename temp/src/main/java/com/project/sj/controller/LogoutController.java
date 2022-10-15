package com.project.sj.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="logoutController",value="/logout")
public class LogoutController extends UserController{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("logout ... get");
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        System.out.println("로그아웃 합니다");
        resp.sendRedirect("/");
    }
}
