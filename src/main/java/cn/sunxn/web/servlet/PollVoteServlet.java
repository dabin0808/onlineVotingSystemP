package cn.sunxn.web.servlet;

import cn.sunxn.domain.Option;
import cn.sunxn.domain.Subject;
import cn.sunxn.service.OptionService;
import cn.sunxn.service.SubjectService;
import cn.sunxn.service.TimeService;
import cn.sunxn.service.impl.OptionServiceImpl;
import cn.sunxn.service.impl.SubjectServiceImpl;
import cn.sunxn.service.impl.TimeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@WebServlet("/pollVoteServlet")
public class PollVoteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String voteTitle = request.getParameter("voteTitle");
        String voteDiscription = request.getParameter("voteDiscription");
        String[] voteContents = request.getParameterValues("voteContent");
        String select = request.getParameter("select");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String userId = request.getParameter("userId");

        Map<String, String[]> map = request.getParameterMap();
        System.out.println();

        Subject subject = new Subject();
        subject.setTitle(voteTitle);
        subject.setDiscription(voteDiscription);
        subject.setType(Integer.parseInt(select));
        startTime = parseStartTime(startTime);
        subject.setCreateTime(startTime);
        endTime = parseEndTime(startTime, endTime);
        subject.setEndTime(endTime);
        subject.setUserId(Integer.parseInt(userId));
        SubjectService subjectService = new SubjectServiceImpl();
        int subjectId = subjectService.saveSubject(subject);
        subject.setSubjectId(subjectId);
//        System.out.println(subject);

        int number = 1;
        OptionService optionService = new OptionServiceImpl();
        for (int i = 0; i < voteContents.length; i++) {
            if (voteContents[i] != null && !"".equals(voteContents[i])) {
                Option option = new Option();
                option.setContent(voteContents[i]);
                option.setOrders(number);
                number += 1;
                option.setSubjectId(subjectId);
//                System.out.println(option);
                optionService.saveOption(option);
            }
        }
    }

    public String parseStartTime(String startTime) {
        TimeService service = new TimeServiceImpl();
        if (startTime == null || "".equals(startTime)) {
            return service.getFormat(new Date());
        } else {
            return startTime.replace("T", " ");
        }
    }

    public String parseEndTime(String startTime, String endTime) {
        TimeService service = new TimeServiceImpl();
        if (endTime == null || "".equals(endTime)) {
            Date parse = service.getParse(startTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parse);
            calendar.add(Calendar.DATE, 2);
            return service.getFormat(calendar.getTime());
        }
        return endTime.replace("T", " ");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
