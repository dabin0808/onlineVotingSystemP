package cn.sunxn.web.servlet;

import cn.sunxn.domain.ApplicationRightBean;
import cn.sunxn.domain.User;
import cn.sunxn.service.ApplicationRightService;
import cn.sunxn.service.impl.ApplicationRightServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/applicationRightServlet")
public class ApplicationRightServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        ApplicationRightBean rightBean = new ApplicationRightBean();
        rightBean.setUserId(loginUser.getUserId());
        rightBean.setRights(loginUser.getStatus());
        ApplicationRightService applicationService = new ApplicationRightServiceImpl();
        applicationService.saveApplicationRightBean(rightBean);
        response.sendRedirect(request.getContextPath()+"/applicationRight.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
