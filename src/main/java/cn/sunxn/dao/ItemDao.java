package cn.sunxn.dao;

import cn.sunxn.domain.Item;

import java.util.List;

public interface ItemDao {

    List<Item> findBySubjectidAndUserid(int subjectId, int userId);

    void save(Item item);

    List<Item> findBySubjectId(int subjectId);
}
