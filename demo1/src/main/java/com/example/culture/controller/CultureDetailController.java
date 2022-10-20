package com.example.culture.controller;

import com.example.domain.CultureVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name="cultureDetailController",value="/detail")
public class CultureDetailController extends CultureController{
    //------->>예약
    //------->>리뷰

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CultureDetailController.doGet");
        HttpSession session = req.getSession();
        String msg = "";
        try {
            //로그인중에만 detail.jsp 가능하게
            if(session.getAttribute("user")==null){
                msg = URLEncoder.encode("먼저 로그인을 해주세요", StandardCharsets.UTF_8);
                throw new Exception();
            }
            //1. 클릭한 목록의 cno를 가져와 db에서 조회
            //2. request영역에 저장후 detail.jsp로 전달
            @Valid Long cno = Long.valueOf(req.getParameter("cno"));
            System.out.println("cno = " + cno);
            req.setAttribute("culture",cultureService.getCulture(cno));
            req.getRequestDispatcher("detail.jsp").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("culture 조회에 실패했습니다");
            if("".equals(msg)){
                msg = URLEncoder.encode("다시 조회해 주세요", StandardCharsets.UTF_8);
            }
            resp.sendRedirect("/project?msg="+msg);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CultureDetailController.doPost");
        HttpSession session = req.getSession();

        String res_dt = req.getParameter("res_dt"); //2022-10-29
        String cno = req.getParameter("cno");
        CultureVO culture = cultureService.getCulture(Long.valueOf(cno));
        session.setAttribute("culture",culture);
        resp.sendRedirect("/project/reservation?res_dt="+res_dt);
    }
}
