package com.example.culture.controller;

import com.example.common.SingletonProvideUtil;
import com.example.culture.service.CultureService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CultureController extends HttpServlet {
    protected final CultureService cultureService = SingletonProvideUtil.SINGLETON_UTIL.cultureService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
