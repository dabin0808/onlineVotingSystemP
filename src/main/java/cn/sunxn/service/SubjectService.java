package cn.sunxn.service;

import cn.sunxn.domain.PageBean;
import cn.sunxn.domain.Subject;

import java.util.List;
import java.util.Map;

public interface SubjectService {

    /**
     * 存储subject对象，并返回自动增长型的主键id
     * @param subject
     * @return
     */
    public abstract int saveSubject(Subject subject);

    public abstract List<Subject> findSubjectByUserId(int userId);

    public abstract Subject findSubjectBySubjectId(int subjectId);

    public abstract void deleteSubjectBySubjectId(int subjectId);

    /**
     * ParticipateVote中的分页条件查询
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    public abstract PageBean<Subject> findParticipateSubjectByPage(String currentPage, String rows, Map<String, String[]> condition);

    public abstract PageBean<Subject> findViewVoteSubjectByPage(String currentPage, String rows, Map<String, String[]> conditions);
}
