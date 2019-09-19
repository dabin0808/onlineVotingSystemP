package cn.sunxn.web.servlet;

import cn.sunxn.domain.User;
import cn.sunxn.service.UserService;
import cn.sunxn.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");

        String checkcode = request.getParameter("checkcode");
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("code");
        session.removeAttribute("code");
        Map<String, Object> map = new HashMap<>();

        if (code == null || !code.equalsIgnoreCase(checkcode)) {
            map.put("checkFlag",false);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(response.getWriter(),map);
            return;
        }
        map.put("checkFlag", true);

        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserService service = new UserServiceImpl();
        User loginUser = service.login(user);
        if (loginUser == null) {
            map.put("loginFlag",false);
        } else {
            map.put("loginFlag",true);
            session.setAttribute("loginUser",loginUser);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
