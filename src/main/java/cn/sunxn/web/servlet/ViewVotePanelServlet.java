package cn.sunxn.web.servlet;

import cn.sunxn.domain.*;
import cn.sunxn.service.ItemService;
import cn.sunxn.service.TimeService;
import cn.sunxn.service.impl.ItemServiceImpl;
import cn.sunxn.service.impl.TimeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet("/viewVotePanelServlet")
public class ViewVotePanelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        String subjectId = request.getParameter("subjectId");
        int subId = Integer.parseInt(subjectId);

        HttpSession session = request.getSession();
        PageBean<Subject> viewVoteSubject = (PageBean<Subject>) session.getAttribute("viewVoteSubject");
        List<Subject> subjectList = viewVoteSubject.getList();
        User loginUser = (User) session.getAttribute("loginUser");
        TimeService timeService = new TimeServiceImpl();
        ItemService itemService = new ItemServiceImpl();

        for (Subject subject: subjectList) {
            if (subject.getSubjectId() == subId) {
                String endTime = subject.getEndTime().substring(0, 16);
//                System.out.println(endTime);
                Date parse = timeService.getParse(endTime);
                if (parse.getTime() < (new Date()).getTime()) {
                    handleData(subject,response);
                } else if (loginUser != null) {
                    List<Item> items = itemService.findItemBySubjectidAndUserid(subId, loginUser.getUserId());
                    System.out.println(items);
                    if (items.size() != 0) {
                        handleData(subject,response);
                    } else {
                        handleData(null,response);
                    }
                } else {
                    handleData(null,response);
                }
            }
        }
    }

    public void handleData(Subject subject, HttpServletResponse response) {
        List<String> optionNames = new ArrayList<>();
        List<Integer> optionCounts = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(subject);

        if (subject != null) {
            map.put("title",subject.getTitle());
            List<Option> options = subject.getOptions();
            for (Option option: options) {
                optionNames.add(option.getContent());
                optionCounts.add(option.getCounts());
            }
            map.put("labels",optionNames);
            map.put("counts",optionCounts);
            map.put("datas",true);

        } else {
            map.put("datas",false);
        }

        try {
            objectMapper.writeValue(response.getWriter(),map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
