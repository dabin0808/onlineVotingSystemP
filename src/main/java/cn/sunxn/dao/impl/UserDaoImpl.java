package cn.sunxn.dao.impl;

import cn.sunxn.dao.UserDao;
import cn.sunxn.domain.User;
import cn.sunxn.util.JDBCUtils;
import com.sun.tools.corba.se.idl.InterfaceGen;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findByUsername(String username) {
        User user = null;
        try {
            String sql = "select * from tab_user where username = ?";
            user = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),username);
        } catch (Exception e) {}
        return user;
    }

    @Override
    public void save(User user) {
        String sql = "insert into tab_user(userId,username,password) values(null,?,?)";
        jdbcTemplate.update(sql,user.getUsername(),user.getPassword());
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
        try {
            String sql = "select * from tab_user where username = ? and password = ?";
            user = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),username,password);
        } catch (Exception e) {}
        return user;
    }

    @Override
    public User findById(int id) {
        String sql = "select * from tab_user where userId = ?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),id);
    }

    @Override
    public void update(User user) {
        String sql = "update tab_user set password = ?, version = ?, status = ? where userId = ?";
        jdbcTemplate.update(sql,user.getPassword(),user.getVersion(),user.getStatus(),user.getUserId());
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from tab_user where userId = ?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        // 定义模板初始化sql
        String sql = "select count(*) from tab_user where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        Set<String> keySet = condition.keySet();
        List<Object> params = new ArrayList<>();
        for (String key: keySet) {
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            String value = condition.get(key)[0];
            if (value != null && !"".equals(value)) {
                if ("username".equals(key)) {
                    sb.append(" and " + key + " like ? ");
                    params.add("%" + value + "%");
                } else {
                    sb.append(" and " + key + " = ? ");
                    params.add(value);
                }
            }
        }
        System.out.println(sb.toString());
        System.out.println(params);
        return jdbcTemplate.queryForObject(sb.toString(),Integer.class,params.toArray());
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from tab_user where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> params = new ArrayList<>();
        Set<String> keySet = condition.keySet();
        for (String key: keySet) {
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            String value = condition.get(key)[0];
            if (value != null && !"".equals(value)) {
                if ("username".equals(key)) {
                    sb.append(" and " + key + " like ? ");
                    params.add("%" + value + "%");
                } else {
                    sb.append(" and " + key + " = ? ");
                    params.add(value);
                }
            }
        }
        sb.append(" limit ?, ? ");
        params.add(start);
        params.add(rows);
        sql = sb.toString();
        System.out.println(sql);
        System.out.println(params);
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<User>(User.class),params.toArray());
    }
}
