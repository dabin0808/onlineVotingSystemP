package cn.sunxn.service.impl;

import cn.sunxn.dao.ItemDao;
import cn.sunxn.dao.impl.ItemDaoImpl;
import cn.sunxn.domain.Item;
import cn.sunxn.service.ItemService;

import java.util.List;

public class ItemServiceImpl implements ItemService {
    private ItemDao itemDao = new ItemDaoImpl();

    @Override
    public List<Item> findItemBySubjectidAndUserid(int subjectId, int userId) {
        return itemDao.findBySubjectidAndUserid(subjectId, userId);
    }

    @Override
    public void saveItem(Item item) {
        itemDao.save(item);
    }

    @Override
    public boolean isVoted(int subjectId) {
        if (itemDao.findBySubjectId(subjectId).size() == 0) {
            return false;
        }
        return true;
    }
}
