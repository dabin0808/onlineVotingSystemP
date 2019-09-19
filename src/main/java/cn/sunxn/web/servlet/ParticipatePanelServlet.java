package cn.sunxn.web.servlet;

import cn.sunxn.domain.*;
import cn.sunxn.service.ItemService;
import cn.sunxn.service.OptionService;
import cn.sunxn.service.impl.ItemServiceImpl;
import cn.sunxn.service.impl.OptionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/participatePanelServlet")
public class ParticipatePanelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String subjectId = request.getParameter("subjectId");
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        PageBean<Subject> subjectPageBean = (PageBean<Subject>) session.getAttribute("subjectPageBean");
        List<Subject> list = subjectPageBean.getList();
        ArrayList<Integer> orders = new ArrayList<>();
        OptionService optionService = new OptionServiceImpl();
        for (Subject subject: list) {
            if (subject.getSubjectId() == Integer.parseInt(subjectId)) {
                session.setAttribute("participateSub",subject);
                ItemService itemService = new ItemServiceImpl();
                List<Item> items = itemService.findItemBySubjectidAndUserid(subject.getSubjectId(), loginUser.getUserId());
                for (Item item: items) {
                    orders.add(optionService.findOrderByOptionId(item.getOptionId()));
                }
                System.out.println(orders);
                session.setAttribute("orders",orders);
                request.getRequestDispatcher("participateVote.jsp").forward(request,response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
