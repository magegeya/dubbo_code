package com.youle.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youle.dao.CheckItemDao;
import com.youle.entity.PageResult;
import com.youle.entity.QueryPageBean;
import com.youle.pojo.CheckItem;
import com.youle.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
       @Autowired
       private CheckItemDao  checkItemDao;

    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBeang) {
        PageHelper.startPage(queryPageBeang.getCurrentPage(),queryPageBeang.getPageSize());
        Page<CheckItem> page = checkItemDao.selectByCondition(queryPageBeang.getQueryString());
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void delete(Integer checkItemId) {
        //查询当前检查项是否和检查组关联
        long count = checkItemDao.findCountByCheckItemId(checkItemId);
        if(count > 0){
            //当前检查项被引用，不能删除
            throw new RuntimeException("当前检查项被引用，不能删除");
        }
        checkItemDao.deleteById(checkItemId);

    }
    @Override
    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }

    @Override
    public CheckItem findById(Integer checkItemId) {
        return checkItemDao.findById(checkItemId);
    }
    public List<CheckItem> findAll(){
        return  checkItemDao.findAll();
    }

}
