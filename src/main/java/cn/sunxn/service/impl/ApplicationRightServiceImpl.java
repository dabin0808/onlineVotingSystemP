package cn.sunxn.service.impl;

import cn.sunxn.dao.ApplicationRightDao;
import cn.sunxn.dao.impl.ApplicationRightDaoImpl;
import cn.sunxn.domain.ApplicationRightBean;
import cn.sunxn.service.ApplicationRightService;

import java.util.List;

public class ApplicationRightServiceImpl implements ApplicationRightService {
    private ApplicationRightDao rightDao = new ApplicationRightDaoImpl();

    @Override
    public void saveApplicationRightBean(ApplicationRightBean rightBean) {
        ApplicationRightBean bean = rightDao.isExistRightBean(rightBean.getUserId());
        if (bean == null) {
            rightDao.saveRightBean(rightBean);
        }
    }

    @Override
    public List<ApplicationRightBean> findAllRightBean() {
        return rightDao.findAll();
    }

    @Override
    public void deleteApplicationRightByUserId(int userId) {
        rightDao.deleteByUserId(userId);
    }
}
