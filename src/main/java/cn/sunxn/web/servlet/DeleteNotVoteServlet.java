package cn.sunxn.web.servlet;

import cn.sunxn.service.OptionService;
import cn.sunxn.service.SubjectService;
import cn.sunxn.service.impl.OptionServiceImpl;
import cn.sunxn.service.impl.SubjectServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteNotVoteServlet")
public class DeleteNotVoteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String subjectId = request.getParameter("subjectId");
        int subId = Integer.parseInt(subjectId);

        OptionService optionService = new OptionServiceImpl();
        optionService.deleteOptionsBySubjectId(subId);

        SubjectService subjectService = new SubjectServiceImpl();
        subjectService.deleteSubjectBySubjectId(subId);

        response.sendRedirect(request.getContextPath()+"/deleteNotVotedSucceed.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
