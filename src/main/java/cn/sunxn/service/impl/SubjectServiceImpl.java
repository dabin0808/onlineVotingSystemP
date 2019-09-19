package cn.sunxn.service.impl;

import cn.sunxn.dao.SubjectDao;
import cn.sunxn.dao.impl.SubjectDaoImpl;
import cn.sunxn.domain.PageBean;
import cn.sunxn.domain.Subject;
import cn.sunxn.service.SubjectService;

import java.util.List;
import java.util.Map;

public class SubjectServiceImpl implements SubjectService {
    private SubjectDao subjectDao = new SubjectDaoImpl();

    @Override
    public int saveSubject(Subject subject) {
        return subjectDao.save(subject);
    }

    @Override
    public List<Subject> findSubjectByUserId(int userId) {
        return subjectDao.findByUserId(userId);
    }

    @Override
    public Subject findSubjectBySubjectId(int subjectId) {
        return subjectDao.findBySubjectId(subjectId);
    }

    @Override
    public void deleteSubjectBySubjectId(int subjectId) {
        subjectDao.deleteBySubjectId(subjectId);
    }

    @Override
    public PageBean<Subject> findParticipateSubjectByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        // 创建空的PageBean对象
        PageBean<Subject> subjectPageBean = new PageBean<>();
        // 设置参数
        subjectPageBean.setCurrentPage(currentPage);
        subjectPageBean.setRows(rows);
        // 调用subjectDao查询总记录数
        int participateTotalCount = subjectDao.findParticipateTotalCount(condition);
        subjectPageBean.setTotalCount(participateTotalCount);
        // 计算总页码
        int totalPage = ((participateTotalCount%rows)==0) ? participateTotalCount/rows : ((participateTotalCount/rows) + 1);
        if (totalPage == 0) {
            totalPage = 1;
        }
        subjectPageBean.setTotalPage(totalPage);
        System.out.println("总页数："+totalPage);

        System.out.println("当前页："+currentPage);
        int start = (currentPage-1)*rows;
        List<Subject> participateSubjectByPage = subjectDao.findParticipateSubjectByPage(start, rows, condition);
        subjectPageBean.setList(participateSubjectByPage);
        return subjectPageBean;
    }

    @Override
    public PageBean<Subject> findViewVoteSubjectByPage(String _currentPage, String _rows, Map<String, String[]> conditions) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        // 创建空的PageBean对象
        PageBean<Subject> subjectPageBean = new PageBean<>();
        // 设置参数
        subjectPageBean.setCurrentPage(currentPage);
        subjectPageBean.setRows(rows);
        // 调用subjectDao查询总记录数
        int participateTotalCount = subjectDao.findViewVoteTotalCount(conditions);
        subjectPageBean.setTotalCount(participateTotalCount);
        // 计算总页码
        int totalPage = ((participateTotalCount%rows)==0) ? participateTotalCount/rows : ((participateTotalCount/rows) + 1);
        if (totalPage == 0) {
            totalPage = 1;
        }
        subjectPageBean.setTotalPage(totalPage);

        int start = (currentPage-1)*rows;
        List<Subject> participateSubjectByPage = subjectDao.findViewVoteByPage(start,rows,conditions);
        subjectPageBean.setList(participateSubjectByPage);
        return subjectPageBean;
    }


}
