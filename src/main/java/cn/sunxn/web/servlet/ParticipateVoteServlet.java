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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/participateVoteServlet")
public class ParticipateVoteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }
        if (rows == null || "".equals(rows)) {
            rows = "6";
        }
        // 获取条件查询参数
        Map<String, String[]> condition = request.getParameterMap();
//        System.out.println(condition);
        // 调用service查询
        SubjectService subjectService = new SubjectServiceImpl();
        PageBean<Subject> subjectByPage = subjectService.findParticipateSubjectByPage(currentPage, rows, condition);
//        System.out.println(subjectByPage);

        OptionService optionService = new OptionServiceImpl();
        List<Subject> subjects = subjectByPage.getList();
        for (Subject subject: subjects) {
            List<Option> optionsBySubjectId = optionService.findOptionsBySubjectId(subject.getSubjectId());
            subject.setOptions(optionsBySubjectId);
        }
        System.out.println(subjectByPage);
        // 将PageBean存入request
//        request.setAttribute("subjectPageBean",subjectByPage);
        request.getSession().setAttribute("subjectPageBean", subjectByPage);

        // 将查询条件存入request
        request.setAttribute("participateCondition",condition);
        request.getRequestDispatcher("/participateVote.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
