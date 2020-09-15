package com.youle.service;

import com.youle.entity.PageResult;
import com.youle.entity.QueryPageBean;
import com.youle.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {


    public  CheckGroup findById(Integer id) ;

    public void add(CheckGroup checkGroup, Integer[] checkitemIds);

   public PageResult findPage(QueryPageBean queryPageBean);

   public List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

   public void edit(CheckGroup checkGroup, Integer[] checkitemIds);

   public List<CheckGroup> findAll();
}
