package cn.sunxn;

import cn.sunxn.dao.UserDao;
import cn.sunxn.dao.impl.UserDaoImpl;
import cn.sunxn.domain.User;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void test() {
        User user = new User();
        UserDao userDao = new UserDaoImpl();
        user = userDao.findByUsernameAndPassword("sun", "123xyz");
        System.out.println(user);
    }
}
