package com.lss.controller;

import com.lss.controller.frontend.MainPageController;
import com.lss.controller.superadmin.HeadLineOperationController;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @authonr: LeeSongsheng
 * @create: 2020/06/24
 **/
@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    /**
    * @Description: 解析 request 相关请求并处理，获取请求路径、方法，选择合适的 Controller 处理
    * @Param: [request, response]
    * @return: void
    * @Author: LeeSongs
    * @Date: 2020/6/24
    */
    @Override
    protected void service (HttpServletRequest request, HttpServletResponse response) {
        System.out.println("request path is: " + request.getServletPath());
        System.out.println("request method is: " + request.getMethod());
        if (request.getServletPath() == "/frontend/getmainpaeinfo" && request.getMethod() == "GET") {
            new MainPageController().getMainPageInfo(request, response);
        }else if (request.getServletPath() == "/superadmin/addheadline" && request.getMethod() == "POST")
            new HeadLineOperationController().addHeadLine(request, response);
    }
}
