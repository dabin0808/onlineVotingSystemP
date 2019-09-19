package cn.sunxn.dao;

import cn.sunxn.domain.Option;

import java.util.List;

public interface OptionDao {

    /**
     * 存储Option对象
     * @param option
     */
    void save(Option option);

    /**
     * 根据subjectid查找Option对象
     * @param subjectId
     * @return
     */
    List<Option> findBySubjectId(int subjectId);

    int findOrderByOptionId(int optionId);

    void updateCounts(int optionid, int counts);

    void deleteBySubjectId(int subjectId);
}
