package cn.sunxn.dao;

import cn.sunxn.domain.ApplicationRightBean;

import java.util.List;

public interface ApplicationRightDao {

    void saveRightBean(ApplicationRightBean rightBean);

    ApplicationRightBean isExistRightBean(int userId);

    List<ApplicationRightBean> findAll();

    void deleteByUserId(int userId);
}
