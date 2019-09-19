package cn.sunxn.service;

import cn.sunxn.domain.Item;

import java.util.List;

public interface ItemService {

    /**
     * 根据subjectid和当前用户id判断是否投票过
     * @param subjectId
     * @param userId
     * @return
     */
    public abstract List<Item> findItemBySubjectidAndUserid(int subjectId, int userId);

    /**
     * 存储记录对象
     * @param item
     */
    void saveItem(Item item);

    /**
     * 通过主题id判断是否投过票
     * @param subjectId
     * @return
     */
    boolean isVoted(int subjectId);
}
