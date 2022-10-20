package com.example.culture.controller;

import com.example.domain.CultureVO;
import com.example.domain.PageRequest;
import com.example.domain.PageResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@WebServlet(name="cultureListController",value="/list")
public class CultureListController extends CultureController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CultureListController.doGet");
        String paramPage = req.getParameter("page");
        String paramSize = req.getParameter("size");
        PageRequest pageRequest;
        int page;
        int size;
        try {
            //쿼리스트링으로 받아온 page size를 검증후 그에 맞는 PageRequest생성
            if(paramPage==null&&paramSize==null){
                pageRequest = PageRequest.builder().build();
            }
            else if (paramPage==null){
                pageRequest = PageRequest.builder()
                        .size(Integer.parseInt(paramSize))
                        .build();
            }
            else if (paramSize==null) {
                pageRequest = PageRequest.builder()
                        .size(Integer.parseInt(paramPage))
                        .build();
            }
            else{ //paramPage!=null && paramSize!=null
                page = Integer.parseInt(paramPage);
                size = Integer.parseInt(paramSize);
                pageRequest = PageRequest.builder()
                        .page(page)
                        .size(size)
                        .build();
            }
            //생성한 PageRequest객체를 전달해 pageResponse를 받아온다
            PageResponse<CultureVO> pageResponse = cultureService.getCultures(pageRequest);
            //받아온 pageResponse를 list.jsp에 전달
            req.setAttribute("pageResponse",pageResponse);
            req.getRequestDispatcher("list.jsp").forward(req,resp);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            String msg = URLEncoder.encode("다시 시도해주세요", StandardCharsets.UTF_8);
            resp.sendRedirect("/project?msg="+msg);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CultureListController.doPost");
        //리스트 조회하는 서비스 호출
    }
}
