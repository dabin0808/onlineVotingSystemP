package cn.sunxn.dao.impl;

import cn.sunxn.dao.OptionDao;
import cn.sunxn.domain.Option;
import cn.sunxn.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class OptionDaoImpl implements OptionDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public void save(Option option) {
        String sql = "insert into tab_option(optionid,content,orders,subjectId) values(null,?,?,?)";
        jdbcTemplate.update(sql,option.getContent(),option.getOrders(),option.getSubjectId());
    }

    @Override
    public List<Option> findBySubjectId(int subjectId) {
        String sql = "select * from tab_option where subjectId = ?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Option>(Option.class),subjectId);
    }

    @Override
    public int findOrderByOptionId(int optionId) {
        String sql = "select orders from tab_option where optionId = ?";
        return jdbcTemplate.queryForObject(sql,Integer.class,optionId);
    }

    @Override
    public void updateCounts(int optionid, int counts) {
        String sql = "update tab_option set counts = ? where optionId = ?";
        jdbcTemplate.update(sql,counts,optionid);
    }

    @Override
    public void deleteBySubjectId(int subjectId) {
        String sql = "delete from tab_option where subjectId = ?";
        jdbcTemplate.update(sql,subjectId);
    }
}
