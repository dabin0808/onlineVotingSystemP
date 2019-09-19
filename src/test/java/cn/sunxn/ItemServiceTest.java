package cn.sunxn;

import cn.sunxn.service.ItemService;
import cn.sunxn.service.impl.ItemServiceImpl;
import org.junit.Test;

public class ItemServiceTest {

    @Test
    public void test() {
        ItemService itemService = new ItemServiceImpl();
        boolean isVoting = itemService.findItemBySubjectidAndUserid(1, 1);
        System.out.println(isVoting);
    }
}
