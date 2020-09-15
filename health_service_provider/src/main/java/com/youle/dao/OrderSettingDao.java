package com.youle.dao;

import com.youle.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderSettingDao {
   public long findCountByOrderDate(Date orderDate);

    public void editNumberByOrderDate(OrderSetting orderSetting);

   public void add(OrderSetting orderSetting);

   public List<OrderSetting> getOrderSettingByMonth(Map map);
}
