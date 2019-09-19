package cn.sunxn.service;

import cn.sunxn.domain.Option;

import java.util.List;

public interface OptionService {

    public abstract void saveOption(Option option);

    public abstract List<Option> findOptionsBySubjectId(int subjectId);

    public abstract int findOrderByOptionId(int optionId);

    public abstract void updateOptionCounts(int optionId, int counts);

    public abstract void deleteOptionsBySubjectId(int subjectId);
}
