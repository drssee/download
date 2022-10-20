package com.example.reservation.controller;

import com.example.domain.CultureVO;
import com.example.reservation.service.ReservationService;
import com.example.reservation.service.ReservationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;

@WebServlet(name="reservationController",value="/reservation")
public class ReservationController extends HttpServlet {

    //임시코드
    ReservationService reservationService = new ReservationServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ReservationController.doGet");
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ReservationController.doPost");
        try {
            HttpSession session = req.getSession();
            String res_dt = req.getParameter("res_dt"); //2022-10-29
            //세션으로 id
            String id = (String) session.getAttribute("user");
            //세션으로 culture 받고 지우기
            CultureVO culture = (CultureVO) session.getAttribute("culture");
            session.removeAttribute("culture");
            reservationService.reservation(culture,id,res_dt);
        } catch (IllegalStateException e){
            e.printStackTrace();
            String msg = URLEncoder.encode("잘못된 값을 입력하셨습니다", StandardCharsets.UTF_8);
            resp.sendRedirect("/project?msg="+msg);
        } catch (Exception e) {
            e.printStackTrace();
            String msg = URLEncoder.encode("다시 시도해 주세요", StandardCharsets.UTF_8);
            resp.sendRedirect("/project?msg="+msg);
        }
    }
}
