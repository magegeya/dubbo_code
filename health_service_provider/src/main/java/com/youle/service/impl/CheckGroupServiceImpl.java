package com.youle.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youle.dao.CheckGroupDao;
import com.youle.entity.PageResult;
import com.youle.entity.QueryPageBean;
import com.youle.pojo.CheckGroup;
import com.youle.pojo.CheckItem;
import com.youle.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
  @Service(interfaceClass = CheckGroupService.class)
  @Transactional
public class CheckGroupServiceImpl implements CheckGroupService {
     @Autowired
     private   CheckGroupDao checkGroupDao;

      @Override
      public CheckGroup findById(Integer id) {
          return checkGroupDao.findById(id);
      }

      @Override
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        checkGroupDao.add(checkGroup);
        setCheckGroupAndCheckItem(checkGroup.getId(),checkitemIds);
    }

      @Override
      public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<CheckItem> page=checkGroupDao.checkGroupService(queryPageBean.getQueryString());
        
          return new PageResult(page.getTotal(),page.getResult());
      }

      @Override
      public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
          return checkGroupDao.findCheckItemIdsByCheckGroupId(id);
      }

      @Override
      public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {
          //修改检查组的基本信息  操作的表t_checkgroup
          checkGroupDao.edit(checkGroup);
          //删除中间表原有的检查组和检查项的关联关系  操作的表t_checkgroup_checkitem
          checkGroupDao.deleteAssociation(checkGroup.getId());
          //重新去建立检查组和检查项的关联关系
          setCheckGroupAndCheckItem(checkGroup.getId(),checkitemIds);
      }

      @Override
      public List<CheckGroup> findAll() {
         return checkGroupDao.findAll();
      }

      public void  setCheckGroupAndCheckItem (Integer checkGroupId,Integer[] checkitemIds){
       if (checkitemIds !=null && checkitemIds.length>0){
           for (Integer checkitemId : checkitemIds) {
               Map<String,Integer> map=new HashMap<>();
               map.put("checkgroup_id",checkGroupId);
               map.put("checkitem_id",checkitemId);
               checkGroupDao.setCheckGroupAndCheckItem(map);
           }
       }
    }
}
