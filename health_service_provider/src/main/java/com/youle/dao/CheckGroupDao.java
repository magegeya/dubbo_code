package com.youle.dao;

import com.github.pagehelper.Page;
import com.youle.pojo.CheckGroup;
import com.youle.pojo.CheckItem;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {
   public void add(CheckGroup checkGroup);

   public void setCheckGroupAndCheckItem(Map map);

   public Page<CheckItem> checkGroupService(String queryString);

   public List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

   public void edit(CheckGroup checkGroup);

   public void deleteAssociation(Integer id);

   public CheckGroup findById(Integer id);

   public List<CheckGroup> findAll();
}
