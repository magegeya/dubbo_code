package com.youle.service;
import com.youle.entity.PageResult;
import com.youle.entity.QueryPageBean;
import com.youle.pojo.CheckItem;
import java.util.List;
/**
 * 检查项服务接口
 */
public interface CheckItemService {
    public void add(CheckItem checkItem);

    public PageResult findPage(QueryPageBean queryPageBean);

    public void delete(Integer checkItemId);

    public void edit(CheckItem checkItem);

    public CheckItem findById(Integer checkItemId);

    public List<CheckItem> findAll();
}