package cn.sunxn.service;

import cn.sunxn.domain.ApplicationRightBean;

import java.util.List;

public interface ApplicationRightService {

    void saveApplicationRightBean(ApplicationRightBean rightBean);

    List<ApplicationRightBean> findAllRightBean();

    void deleteApplicationRightByUserId(int userId);
}
