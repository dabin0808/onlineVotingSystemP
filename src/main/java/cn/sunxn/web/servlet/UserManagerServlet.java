package cn.sunxn.web.servlet;

import cn.sunxn.domain.ApplicationRightBean;
import cn.sunxn.domain.User;
import cn.sunxn.service.ApplicationRightService;
import cn.sunxn.service.UserService;
import cn.sunxn.service.impl.ApplicationRightServiceImpl;
import cn.sunxn.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userManagerServlet")
public class UserManagerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        ApplicationRightService rightService = new ApplicationRightServiceImpl();
        List<ApplicationRightBean> allRightBean = rightService.findAllRightBean();
        for (ApplicationRightBean rightBean: allRightBean) {
            User user = userService.findUserById(rightBean.getUserId() + "");
            rightBean.setUser(user);
        }
        System.out.println(allRightBean);
        request.setAttribute("allRightBean",allRightBean);
        request.getRequestDispatcher("/userRightManager.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
