package com.example.culture.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="cultureRegisterController",value="/register")
public class CultureRegisterController extends CultureController{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("register ... get");
        req.getRequestDispatcher("register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("register ... post");
        //현재 culture의 모든 데이터를 지우고 다시 등록
        cultureService.removeAll();
        cultureService.register();
        //
        System.out.println("db 최신화 성공");
        resp.sendRedirect("/project");
    }
}
