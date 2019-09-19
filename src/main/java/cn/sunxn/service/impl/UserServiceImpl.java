package cn.sunxn.service.impl;

import cn.sunxn.dao.UserDao;
import cn.sunxn.dao.impl.UserDaoImpl;
import cn.sunxn.domain.PageBean;
import cn.sunxn.domain.User;
import cn.sunxn.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean register(User user) {
        // 根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        if (u != null) {
            // 用户名存在，注册失败
            return false;
        }
        userDao.save(user);
        return true;
    }

    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public User findUserById(String id) {
        return userDao.findById(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public void deleteUser(String id) {
        userDao.deleteById(Integer.parseInt(id));
    }

    @Override
    public void delSelectedUser(String[] ids) {
        if (ids != null && ids.length > 0) {
            for (String id: ids) {
                userDao.deleteById(Integer.parseInt(id));
            }
        }
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        PageBean<User> pageBean = new PageBean<>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setRows(rows);
        int totalCount = userDao.findTotalCount(condition);
//        System.out.println(totalCount);
        pageBean.setTotalCount(totalCount);
        int totalPage = (totalCount%rows)==0 ? (totalCount/rows): ((totalCount/rows)+1);
        if (totalPage == 0) {
            totalPage = 1;
        }
        pageBean.setTotalPage(totalPage);
        int start = (currentPage - 1)*rows;
        List<User> userList = userDao.findByPage(start, rows, condition);
        pageBean.setList(userList);
        return pageBean;
    }
}
