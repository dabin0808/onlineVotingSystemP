package cn.sunxn.web.servlet;

import cn.sunxn.domain.Option;
import cn.sunxn.domain.PageBean;
import cn.sunxn.domain.Subject;
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
import java.util.List;
import java.util.Map;

@WebServlet("/viewVoteServlet")
public class ViewVoteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }
        if (rows == null || "".equals(rows)) {
            rows = "6";
        }
        Map<String, String[]> viewVoteCondition = request.getParameterMap();
        SubjectService subjectService = new SubjectServiceImpl();
        PageBean<Subject> viewVoteSubjectByPage = subjectService.findViewVoteSubjectByPage(currentPage, rows, viewVoteCondition);
//        System.out.println(viewVoteSubjectByPage.getList().size()+"大小");

        OptionService optionService = new OptionServiceImpl();
        List<Subject> subjects = viewVoteSubjectByPage.getList();
        for (Subject subject: subjects) {
            List<Option> optionsBySubjectId = optionService.findOptionsBySubjectId(subject.getSubjectId());
            subject.setOptions(optionsBySubjectId);
        }

        request.getSession().setAttribute("viewVoteSubject",viewVoteSubjectByPage);
        request.setAttribute("viewVoteCondition",viewVoteCondition);
        request.getRequestDispatcher("/viewVote.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
