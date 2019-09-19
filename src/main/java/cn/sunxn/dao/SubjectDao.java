package cn.sunxn.dao;

import cn.sunxn.domain.Subject;

import java.util.List;
import java.util.Map;

public interface SubjectDao {

    /**
     * 存储subject对象，并返回主键id
     * @param subject
     * @return
     */
    int save(Subject subject);

    List<Subject> findByUserId(int userId);

    Subject findBySubjectId(int subjectId);

    void deleteBySubjectId(int subjectId);

    /**
     * 查询可以参与投票主题的总记录数
     * @param condition
     * @return
     */
    int findParticipateTotalCount(Map<String, String[]> condition);

    /**
     * 分页查询ParticipateVoting每页记录
     * @param start
     * @param rows
     * @param condition
     * @return
     */
    List<Subject> findParticipateSubjectByPage(int start, int rows, Map<String, String[]> condition);

    /**
     * 查询查看投票主题的总记录数
     * @param conditions
     * @return
     */
    int findViewVoteTotalCount(Map<String, String[]> conditions);

    /**
     * 分页查询viewVote中的subject对象
     * @param start
     * @param rows
     * @param conditions
     * @return
     */
    List<Subject> findViewVoteByPage(int start, int rows, Map<String, String[]> conditions);
}
