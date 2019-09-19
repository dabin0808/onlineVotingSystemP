package cn.sunxn.web.servlet;

import cn.sunxn.domain.ApplicationRightBean;
import cn.sunxn.domain.User;
import cn.sunxn.service.ApplicationRightService;
import cn.sunxn.service.UserService;
import cn.sunxn.service.impl.ApplicationRightServiceImpl;
import cn.sunxn.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/updateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
//        System.out.println(user);
        UserService userService = new UserServiceImpl();
        userService.updateUser(user);

        ApplicationRightService rightService = new ApplicationRightServiceImpl();
        rightService.deleteApplicationRightByUserId(user.getUserId());

        request.getRequestDispatcher("/findUserByPageServlet").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
