package com.example.user.controller;



import com.example.domain.UserVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "userJoinController", value = "/join")
public class UserJoinController extends UserController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("register ... get");
        req.getRequestDispatcher("join.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("register ... post");

        HttpSession session = req.getSession();
        String msg = "";

        //폼태그 입력값 유저객체에 저장
        try {
            String phone = req.getParameter("phone1")
                    + req.getParameter("phone2")
                    + req.getParameter("phone3");
            Integer age = Integer.parseInt(req.getParameter("age"));
            Integer gender = Integer.parseInt(req.getParameter("gender"));
            UserVO userVO = UserVO.builder()
                    .id(req.getParameter("id"))
                    .pwd(req.getParameter("pwd"))
                    .name(req.getParameter("name"))
                    .email(req.getParameter("email"))
                    .phone(phone)
                    .age(age)
                    .gender(gender)
                    .build();
            //회원가입후 세션에 저장
            userService.join(userVO);
            //회원 로그인 기억 세션
            session.setAttribute("user", userVO.getId());
            System.out.println("회원가입 성공");
            resp.sendRedirect("/project");
        } catch (IOException | RuntimeException e) {
            //회원가입 실패시 join ... get
            e.printStackTrace();
            msg = URLEncoder.encode("회원가입에 실패했습니다(입력값을 확인해주세요)", StandardCharsets.UTF_8);
            resp.sendRedirect("/project/join?msg="+msg);
        }
    }
}
