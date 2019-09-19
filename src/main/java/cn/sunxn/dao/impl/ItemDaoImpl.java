package cn.sunxn.dao.impl;

import cn.sunxn.dao.ItemDao;
import cn.sunxn.domain.Item;
import cn.sunxn.domain.Subject;
import cn.sunxn.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ItemDaoImpl implements ItemDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Item> findBySubjectidAndUserid(int subjectId, int userId) {
        String sql = "select * from tab_item where subjectId = ? and userId = ?";
        try {
            return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Item>(Item.class),subjectId,userId);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void save(Item item) {
        String sql = "insert into tab_item(itemId,subjectId,optionId,userId) values(null,?,?,?)";
        jdbcTemplate.update(sql,item.getSubjectId(),item.getOptionId(),item.getUserId());
    }

    @Override
    public List<Item> findBySubjectId(int subjectId) {
        String sql = "select * from tab_item where subjectId = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Item>(Item.class),subjectId);
    }
}
