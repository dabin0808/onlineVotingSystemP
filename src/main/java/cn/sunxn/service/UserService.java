package cn.sunxn.service;

import cn.sunxn.domain.PageBean;
import cn.sunxn.domain.User;

import java.util.Map;

public interface UserService {

    /**
     * 注册用户
     * @param user
     * @return
     */
    public abstract boolean register(User user);

    /**
     * 用户登录，并返回用户对象
     * @param user
     * @return
     */
    public abstract User login(User user);

    User findUserById(String id);

    void updateUser(User user);

    void deleteUser(String id);

    /**
     * 批量删除用户
     * @param ids
     */
    void delSelectedUser(String[] ids);

    /**
     * 分页条件查询
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
}
