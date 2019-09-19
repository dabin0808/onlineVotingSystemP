package cn.sunxn.web.servlet;

import cn.sunxn.domain.Item;
import cn.sunxn.domain.Option;
import cn.sunxn.domain.Subject;
import cn.sunxn.domain.User;
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

@WebServlet("/countOneVotingServlet")
public class CountOneVotingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String radioGroup = request.getParameter("RadioGroup");
        int index = Integer.parseInt(radioGroup);
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        Subject participateSub = (Subject) session.getAttribute("participateSub");
        Option option = participateSub.getOptions().get(index);
        option.setCounts(option.getCounts() + 1);
        OptionService optionService = new OptionServiceImpl();
        optionService.updateOptionCounts(option.getOptionId(),option.getCounts());

        Item item = new Item();
        item.setSubjectId(participateSub.getSubjectId());
        item.setUserId(loginUser.getUserId());
        item.setOptionId(option.getOptionId());
        System.out.println(item);
        ItemService itemService = new ItemServiceImpl();
        itemService.saveItem(item);
        request.getRequestDispatcher("/participatePanelServlet?subjectId="+participateSub.getSubjectId()).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
