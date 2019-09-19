package cn.sunxn.web.servlet;

import cn.sunxn.domain.Subject;
import cn.sunxn.domain.User;
import cn.sunxn.service.ItemService;
import cn.sunxn.service.SubjectService;
import cn.sunxn.service.impl.ItemServiceImpl;
import cn.sunxn.service.impl.SubjectServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/maintainSelfVoteServlet")
public class MaintainSelfVoteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        SubjectService subjectService = new SubjectServiceImpl();
        List<Subject> subjects = subjectService.findSubjectByUserId(loginUser.getUserId());
        System.out.println(subjects);
        ItemService itemService = new ItemServiceImpl();
        List<Subject> notVoted = new ArrayList<>();
        for (Subject subject: subjects) {
            if (itemService.isVoted(subject.getSubjectId()) == false) {
                notVoted.add(subject);
            }
        }
        System.out.println(notVoted);
        request.setAttribute("notVotedSubject",notVoted);
        request.getRequestDispatcher("/maintainSelfVote.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
