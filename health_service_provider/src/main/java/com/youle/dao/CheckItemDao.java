package com.youle.dao;

import com.github.pagehelper.Page;
import com.youle.pojo.CheckItem;

import java.util.List;

/**
        * 持久层Dao接口
        */
public interface CheckItemDao {

    public void add(CheckItem checkItem);

    public Page<CheckItem> selectByCondition(String queryString);

    public long findCountByCheckItemId(Integer checkItemId);

   public   void deleteById(Integer checkItemId);

   public void edit(CheckItem checkItem);

   public CheckItem findById(Integer checkItemId);

   public List<CheckItem> findAll();
}
