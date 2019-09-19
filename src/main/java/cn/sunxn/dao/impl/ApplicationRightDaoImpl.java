package cn.sunxn.dao.impl;

import cn.sunxn.dao.ApplicationRightDao;
import cn.sunxn.domain.ApplicationRightBean;
import cn.sunxn.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ApplicationRightDaoImpl implements ApplicationRightDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public void saveRightBean(ApplicationRightBean rightBean) {
        String sql = "insert into tab_applicationRight(applicationId,userId,rights) values(null,?,?)";
        jdbcTemplate.update(sql,rightBean.getUserId(),rightBean.getRights());
    }

    @Override
    public ApplicationRightBean isExistRightBean(int userId) {
        ApplicationRightBean rightBean = null;
        String sql = "select * from tab_applicationRight where userId = ?";
        try {
            rightBean = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<ApplicationRightBean>(ApplicationRightBean.class),userId);
        } catch (Exception e) {

        }
        return rightBean;
    }

    @Override
    public List<ApplicationRightBean> findAll() {
        String sql = "select * from tab_applicationRight";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<ApplicationRightBean>(ApplicationRightBean.class));
    }

    @Override
    public void deleteByUserId(int userId) {
        String sql = "delete from tab_applicationRight where userId = ?";
        jdbcTemplate.update(sql,userId);
    }

}
