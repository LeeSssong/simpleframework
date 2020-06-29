package com.lss;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @authonr: LeeSongsheng
 * @create: 2020/06/23
 **/
@Slf4j
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    //只会被执行一次，即初始化后会一直存在于容器当中
    @Override
    public void init() {
        System.out.println("初始化 Servlet...");
    }

    @Override
    protected void service (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet 入口");
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = "abcde";
        log.debug("name is " + name);
        request.setAttribute("name", name);
        request.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("Destroy...");
    }
}
