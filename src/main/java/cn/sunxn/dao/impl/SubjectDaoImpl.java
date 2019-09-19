package cn.sunxn.dao.impl;

import cn.sunxn.dao.SubjectDao;
import cn.sunxn.domain.Subject;
import cn.sunxn.service.TimeService;
import cn.sunxn.service.impl.TimeServiceImpl;
import cn.sunxn.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class SubjectDaoImpl implements SubjectDao {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private TimeService timeService = new TimeServiceImpl();
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int save(Subject subject) {
        String sql = "insert into tab_subject(subjectId,title,discription,type,createTime,endTime,userId) values(null,?,?,?,?,?,?)";
        int key = 0;
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,subject.getTitle());
            pstmt.setString(2,subject.getDiscription());
            pstmt.setInt(3,subject.getType());
            pstmt.setString(4,subject.getCreateTime());
            pstmt.setString(5,subject.getEndTime());
            pstmt.setInt(6,subject.getUserId());
            pstmt.executeUpdate();
            // 检索由于执行此Statement对象而创建的所有自动生成的键
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                key = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, pstmt, rs);
        }
        return key;
    }

    @Override
    public List<Subject> findByUserId(int userId) {
        String sql = "select * from tab_subject where userId = ?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Subject.class),userId);
    }

    @Override
    public Subject findBySubjectId(int subjectId) {
        Subject subject = null;
        try {
            String sql = "select * from tab_subject where subjectId = ?";
            subject = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Subject>(Subject.class),subjectId);
        } catch (Exception e) {

        }
        return subject;
    }

    @Override
    public void deleteBySubjectId(int subjectId) {
        String sql = "delete from tab_subject where subjectId = ?";
        jdbcTemplate.update(sql,subjectId);
    }

    @Override
    public int findParticipateTotalCount(Map<String, String[]> condition) {
        // 定义模板 初始化sql
        String sql = "select count(*) from tab_subject where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        // 遍历condition
        Set<String> keySet = condition.keySet();
        // 定义参数的集合
        List<Object> params = new ArrayList<>();

        String currentTime = timeService.getFormat(new Date());
        sb.append(" and endTime > ? ");
        params.add(currentTime);
        sb.append(" and createTime < ? ");
        params.add(currentTime);

        for (String key: keySet) {
            // 排除分页条件参数
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            String value = condition.get(key)[0];
            if (value != null && !"".equals(value)) {
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%");
            }
        }
        System.out.println(sb.toString());
        System.out.println(params);
        return jdbcTemplate.queryForObject(sb.toString(),Integer.class, params.toArray());
    }

    @Override
    public List<Subject> findParticipateSubjectByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from tab_subject where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> params = new ArrayList<>();
        Set<String> keySet = condition.keySet();

        String currentTime = timeService.getFormat(new Date());
        sb.append(" and endTime > ? ");
        params.add(currentTime);
        sb.append(" and createTime < ? ");
        params.add(currentTime);

        for (String key: keySet) {
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            String value = condition.get(key)[0];
            if (value != null && !"".equals(value)) {
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%");
            }
        }
        // 添加分页查询
        sb.append(" limit ?, ? ");
        // 添加分页查询参数值
        params.add(start);
        params.add(rows);
        sql = sb.toString();
        System.out.println(sql);
        System.out.println(params);
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Subject>(Subject.class),params.toArray());
    }

    @Override
    public int findViewVoteTotalCount(Map<String, String[]> conditions) {
        String sql = "select count(*) from tab_subject where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        Set<String> keySet = conditions.keySet();
        List<Object> params = new ArrayList<>();

//        sb.append(" and endTime < ? ");
//        params.add(timeService.getFormat(new Date()));

        for (String key: keySet) {
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            String value = conditions.get(key)[0];
            if (value != null && !"".equals(value)) {
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%");
            }
        }
        System.out.println(sb.toString());
        System.out.println(params);
        return jdbcTemplate.queryForObject(sb.toString(),Integer.class, params.toArray());
    }

    @Override
    public List<Subject> findViewVoteByPage(int start, int rows, Map<String, String[]> conditions) {
        String sql = "select * from tab_subject where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> params = new ArrayList<>();
        Set<String> keySet = conditions.keySet();
        /*sb.append(" and endTime < ? ");
        params.add(timeService.getFormat(new Date()));*/

        for (String key: keySet) {
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            String value = conditions.get(key)[0];
            if (value != null && !"".equals(value)) {
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%");
            }
        }
        // 添加分页查询
        sb.append(" limit ?, ? ");
        // 添加分页查询参数值
        params.add(start);
        params.add(rows);
        sql = sb.toString();
        System.out.println(sql);
        System.out.println(params);
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Subject>(Subject.class),params.toArray());
    }

}
