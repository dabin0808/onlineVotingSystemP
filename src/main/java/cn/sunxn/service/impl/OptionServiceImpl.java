package cn.sunxn.service.impl;

import cn.sunxn.dao.OptionDao;
import cn.sunxn.dao.impl.OptionDaoImpl;
import cn.sunxn.domain.Option;
import cn.sunxn.service.OptionService;

import java.util.List;

public class OptionServiceImpl implements OptionService {
    private OptionDao optionDao = new OptionDaoImpl();

    @Override
    public void saveOption(Option option) {
        optionDao.save(option);
    }

    @Override
    public List<Option> findOptionsBySubjectId(int subjectId) {
        return optionDao.findBySubjectId(subjectId);
    }

    @Override
    public int findOrderByOptionId(int optionId) {
        return optionDao.findOrderByOptionId(optionId);
    }

    @Override
    public void updateOptionCounts(int optionId, int counts) {
        optionDao.updateCounts(optionId,counts);
    }

    @Override
    public void deleteOptionsBySubjectId(int subjectId) {
        optionDao.deleteBySubjectId(subjectId);
    }
}
