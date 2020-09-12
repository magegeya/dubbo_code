package com.youle.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import  com.youle.constant.MessageConstant;
import com.youle.entity.PageResult;
import com.youle.entity.QueryPageBean;
import com.youle.entity.Result;
import com.youle.pojo.CheckItem;
import  com.youle.service.CheckItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
/**
 * 体检检查项管理
 */
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {
    @Reference
    private CheckItemService checkItemService;
    @RequestMapping("/findPage")
    public  PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult= checkItemService.findPage(queryPageBean);
        return pageResult;
    }

    //新增
    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){
        try {
        checkItemService.add(checkItem);
    }catch (Exception e){
        return new Result(false,MessageConstant.ADD_CHECKITEM_FAIL);
    }
        return new Result(true,MessageConstant.ADD_CHECKITEM_SUCCESS);
    }
    //删除
    @RequestMapping("/delete")
    public Result delete(@RequestParam("id") Integer checkItemId){
        try {
        checkItemService.delete(checkItemId);
            return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);

    }catch (Exception e){
        return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
    }
    }


    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckItem checkItem){
        try {
            checkItemService.edit(checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            //编辑检查项失败
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

    @RequestMapping("/findById")
    public Result findById(@RequestParam("id") Integer checkItemId){
        try {
            CheckItem checkItem = checkItemService.findById(checkItemId);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            //根据id查询检查项失败
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

}
