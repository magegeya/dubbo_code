package com.youle.dao;

import com.github.pagehelper.Page;
import com.youle.pojo.Setmeal;

import java.util.Map;

public interface SetmealDao {
   public void add(Setmeal setmeal);

   public void setSetmealAndCheckGroup(Map<String, Integer> map);

   public Page<Setmeal> findByCondition(String queryString);
}
